package sgg.qin.framework.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import sgg.qin.domain.Page;
import sgg.qin.util.SerializeUtil;

public class RedisCache implements Cache {

	private String name;

	// ShardedJedis工具类
	private RedisClientTemplate redisClientTemplate;

	public RedisCache() {
		super();
	}

	public RedisCache(String name, RedisClientTemplate redisClientTemplate) {
		super();
		this.name = name;
		this.redisClientTemplate = redisClientTemplate;
	}
	
	//缓存的名字 
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	//得到底层使用的缓存
	@Override
	public Object getNativeCache() {
		// TODO Auto-generated method stub
		return this;
	}
	//根据key得到一个ValueWrapper，然后调用其get方法获取值
	@Override
	public ValueWrapper get(Object key) {
		// TODO Auto-generated method stub
		Object object = null;
		if (redisClientTemplate.hexists(name, key + "")) {
			System.out.println("从缓存中获取数据");
			byte[] hget = redisClientTemplate.hget(name.getBytes(), (key + "").getBytes());
			object = SerializeUtil.deserialize(hget);
		}
		return toValueWrapper(object);
	}
	
	//根据key，和value的类型直接获取value
	@Override
	public <T> T get(Object key, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}
	//往缓存放数据 
	@Override
	public void put(Object key, Object value) {
		// TODO Auto-generated method stub
		System.out.println("往缓存放数据 key:" + name + "  fieldkey:" + key);
		byte[] serialize = SerializeUtil.serialize(value);
		redisClientTemplate.hset(name.getBytes(), (key + "").getBytes(), serialize);
		redisClientTemplate.expire(name, 3600);
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		// TODO Auto-generated method stub
		return toValueWrapper(null);
	}

	//从缓存中移除key对应的缓存
	@Override
	public void evict(Object key) {
		// TODO Auto-generated method stub
		System.out.println("从缓存中移除fieldkey对应的缓存 key:" + name + "  fieldkey:" + key);
		redisClientTemplate.del(key + "");
		redisClientTemplate.hdel(name, key + "");
	}
	//清空缓存  
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		System.out.println("清空缓存 key:" + name);
		redisClientTemplate.del(name);

	}

	private ValueWrapper toValueWrapper(Object element) {
		return (element != null ? new SimpleValueWrapper(element) : null);
	}

	// 获取缓存中所有的key
	public Set keys() {
		Set<Object> set=new LinkedHashSet<>();
		Set<byte[]> hkeys2 = redisClientTemplate.hkeys(name.getBytes());
		for (byte[] bs : hkeys2) {
			set.add(SerializeUtil.deserialize(bs));
		}
		return set;
	}

	// 获取缓存中所有的value
	public Collection values() {
		Collection<Object> con=new ArrayList<>();
		Collection<byte[]> hvals = redisClientTemplate.hvals(name.getBytes());
		for (byte[] bs : hvals) {
			con.add(SerializeUtil.deserialize(bs));
		}
		return con;
	}
	
	 //返回缓存大小  
    public int size() {
    	int hlen = redisClientTemplate.hlen(name.getBytes()).intValue();
    	return hlen;
    }
    

}
