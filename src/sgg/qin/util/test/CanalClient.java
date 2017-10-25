package sgg.qin.util.test;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Properties;


import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;

import redis.clients.jedis.Jedis;
import sgg.qin.framework.redis.RedisClientTemplate;
import sgg.qin.util.PropertiesUtil;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.client.*;
//Canal客户端
public class CanalClient {
	
	@Autowired
	private RedisClientTemplate redisClientTemplate;
	
	public void runCanalClient() {
	    new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ListeningCanal();
			}},"Thread-Canal").start();
	}

	public void ListeningCanal() {
		// AddressUtils.getHostIp()
		CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("10.211.55.19", 11111),
				"example", "", "");
		int batchSize = 1000;
		try {
			connector.connect();
			connector.subscribe(".*\\..*");
			connector.rollback();
			while (true) {
				/*
				 * 获取指定数量的数据 一次获取多少条 该方法有个重载方法：getWithoutAck(int batchSize, Long timeout,
				 * TimeUnit unit) 相比于getWithoutAck(int batchSize)，允许设定获取数据的timeout超时时间
				 * 拿够batchSize条记录或者超过timeout时间 timeout=0，阻塞等到足够的batchSize
				 */
				Message message = connector.getWithoutAck(batchSize);
				long batchId = message.getId();
				try {
					int size = message.getEntries().size();
					if (batchId == -1 || size == 0) {
						try {
							Thread.currentThread().sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						printEntry(message.getEntries());
					}
					connector.ack(batchId); // 确认已经消费成功，通知server删除数据。基于get获取的batchId进行提交，避免误操作
				} catch (CanalClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					connector.rollback(batchId); // 回滚上次的get请求，重新获取数据。基于get获取的batchId进行提交，避免误操作
				}
			}
		} finally {
			connector.disconnect();
		}
	}

	private  void printEntry(List<Entry> entrys) {
		for (Entry entry : entrys) {
			// 为什么要跳出循环 可以看entrys对象 entrys对象中EntryType不等于TRANSACTIONBEGIN
			// 和TRANSACTIONEND才是我们要的数据
			if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN
					|| entry.getEntryType() == EntryType.TRANSACTIONEND) {

				continue;
			}
			RowChange rowChage = null;
			try {
				rowChage = RowChange.parseFrom(entry.getStoreValue());
			} catch (Exception e) {
				throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
						e);
			}
			EventType eventType = rowChage.getEventType();
			for (RowData rowData : rowChage.getRowDatasList()) {
				String valueByKey = PropertiesUtil.GetValueByKey("canal.properties",entry.getHeader().getSchemaName() + "." + entry.getHeader().getTableName());
				String tablename=entry.getHeader().getTableName().toLowerCase();
				if (Boolean.parseBoolean(valueByKey)) {
					if (eventType == EventType.DELETE) {
						redisDelete(rowData,tablename);
					} else if (eventType == EventType.INSERT) {
						redisInsert(rowData,tablename);
					} else if (eventType == EventType.UPDATE) {
						redisUpdate(rowData,tablename);
					}
				}

			}
		}
	}
	// 修改
	private  void redisUpdate(RowData rowData,String tablename) {
		if (rowData.getAfterColumnsList().size() > 0&&redisClientTemplate.exists(tablename)) {
			JSONObject json = new JSONObject();
			//修改后的ID
			String Afterid=null;
			String Beforeid=null;
			for (Column column : rowData.getAfterColumnsList()) {
				if (column.getIsKey()) {
					Afterid=column.getValue();
				}
				json.put(column.getName().toLowerCase(), column.getValue());
			}
			for (Column column : rowData.getBeforeColumnsList()) {
				if (column.getIsKey()) {
					Beforeid=column.getValue();
					break;
				}
			}
			if (!Afterid.equals(Beforeid)) {
				redisClientTemplate.hdel(tablename.toLowerCase(), tablename.toLowerCase()+":"+Beforeid);
			}
			redisClientTemplate.hset(tablename.toLowerCase(), tablename.toLowerCase()+":"+Afterid, json.toJSONString());

			
		}
	}
	// 添加
	private  void redisInsert(RowData rowData,String tablename) {

		if (rowData.getAfterColumnsList().size() > 0&&redisClientTemplate.exists(tablename)) {
			JSONObject json = new JSONObject();
			String id=null;
			for (Column column : rowData.getAfterColumnsList()) {
				if (column.getIsKey()) {
					id=column.getValue();
				}
				json.put(column.getName().toLowerCase(), column.getValue());
			}
			redisClientTemplate.hset(tablename.toLowerCase(), tablename.toLowerCase()+":"+id, json.toJSONString());
			}
	}

	// 删除
	private  void redisDelete(RowData rowData,String tablename) {
		if (rowData.getBeforeColumnsList().size() > 0&&redisClientTemplate.exists(tablename)) {
			for (Column column : rowData.getBeforeColumnsList()) {
				if (column.getIsKey()) {
					redisClientTemplate.hdel(tablename.toLowerCase(), tablename.toLowerCase()+":"+column.getValue());
					break;
				}
			}
		}
	}
}
