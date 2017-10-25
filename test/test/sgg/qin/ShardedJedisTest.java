package test.sgg.qin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.SortingParams;
import sgg.qin.framework.redis.RedisClientTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/ApplicationContext.xml")
public class ShardedJedisTest {
	@Autowired
	private RedisClientTemplate redisClientTemplate;



	
	
	@Test
	public void testfill() {

		String key = "testTransaction";

		ShardedJedis shardedJedis = redisClientTemplate.getRedisDataSource().getShardedJedis();
		shardedJedis.set("xiaohei", "heihei");
		redisClientTemplate.set("xiaohei2", "xiao");

		// 开启通道

		ShardedJedisPipeline shardedJedisPipeline = shardedJedis.pipelined();

		for (int i = 0; i < 10; i++) {
			shardedJedisPipeline.set(key + "_" + i, "value1");
			shardedJedisPipeline.expire(key + "_" + i, 60 * 10);
		}
		System.out.println("-----------------------");
		// 提交通道内容并返回内容
		List<Object> list = shardedJedisPipeline.syncAndReturnAll();
		System.out.println("listSize=" + list.size());
		int index = 0;
		for (Object obj : list) {
			System.out.println((++index) + "obj=" + obj);
		}
		redisClientTemplate.getRedisDataSource().returnResource(shardedJedis);

	}

	/**
	 * 键操作
	 */
	@Test
	public void testKey() throws InterruptedException {
		String key = "xiaohei";
		System.out.println("判断某个键是否存在：" + redisClientTemplate.exists(key));
		System.out.println("新增<'xiaohei','pwsvalue'>:" + redisClientTemplate.set(key, "pwsvalue"));
		System.out.println("判断某个键是否存在：" + redisClientTemplate.exists(key));
		System.out.println("新增<'psw','pwsvalue'>的键值对：" + redisClientTemplate.set("psw", "pswvalue"));
		System.out.println("系统中所有的键如下：");
		System.out.println("删除键psw:" + redisClientTemplate.del("psw"));
		System.out.println("判断键psw是否存在：" + redisClientTemplate.exists("psw"));
		System.out.println("设置键keyname的过期时间为5s:" + redisClientTemplate.expire(key, 5));
		Thread.sleep(10);
		System.out.println("查看键keyname的剩余生存时间：" + redisClientTemplate.ttl(key));
		System.out.println("移除键keyname的生存时间：" + redisClientTemplate.persist(key));
		System.out.println("查看键keyname的剩余生存时间：" + redisClientTemplate.ttl(key));
		System.out.println("查看键keyname所存储的值的类型：" + redisClientTemplate.type(key));

	}

	/**
	 * 字符串操作
	 */
	@Test
	public void testString() throws Exception {
		System.out.println("===========增加数据===========");
		System.out.println(redisClientTemplate.set("key1", "value1"));
		System.out.println(redisClientTemplate.set("key2", "value2"));
		System.out.println(redisClientTemplate.set("key3", "value3"));
		System.out.println("删除键key2:" + redisClientTemplate.del("key2"));
		System.out.println("获取键key2:" + redisClientTemplate.get("key2"));
		System.out.println("修改key1:" + redisClientTemplate.set("key1", "value1Changed"));
		System.out.println("获取key1的值：" + redisClientTemplate.get("key1"));
		System.out.println("key3的值：" + redisClientTemplate.get("key3"));
		System.out.println("在key3后面加入值：" + redisClientTemplate.append("key3", "End"));
		System.out.println("key3的值：" + redisClientTemplate.get("key3"));

		System.out.println("===========新增键值对防止覆盖原先值==============");
		System.out.println(redisClientTemplate.setnx("key1", "value1"));
		System.out.println(redisClientTemplate.setnx("key2", "value2"));
		System.out.println(redisClientTemplate.setnx("key2", "value2-new"));
		System.out.println("key1的值：" + redisClientTemplate.get("key1"));
		System.out.println("key2的值：" + redisClientTemplate.get("key2"));

		System.out.println("===========新增键值对并设置有效时间=============");
		System.out.println(redisClientTemplate.setex("key3", 2, "value3"));
		System.out.println(redisClientTemplate.get("key3"));
		Thread.sleep(2000);
		System.out.println(redisClientTemplate.get("key3"));

		System.out.println("===========获取原值，更新为新值==========");
		// GETSET is an atomic set this value and return the old value command.
		System.out.println(redisClientTemplate.getSet("key2", "key2GetSet"));
		System.out.println(redisClientTemplate.get("key2"));
		System.out.println("获得key2的值的字串：" + redisClientTemplate.getrange("key2", 2, 4));
	}

	/**
	 * 整数和浮点数
	 */
	@Test
	public void testIntFloat() {
		redisClientTemplate.set("key1", "1");
		redisClientTemplate.set("key2", "2");
		redisClientTemplate.set("key3", "2.3");
		System.out.println("key1的值：" + redisClientTemplate.get("key1"));
		System.out.println("key2的值：" + redisClientTemplate.get("key2"));
		System.out.println("key1的值加1：" + redisClientTemplate.incr("key1"));
		System.out.println("获取key1的值：" + redisClientTemplate.get("key1"));
		System.out.println("key2的值减1：" + redisClientTemplate.decr("key2"));
		System.out.println("获取key2的值：" + redisClientTemplate.get("key2"));
		System.out.println("将key1的值加上整数5：" + redisClientTemplate.incrBy("key1", 5));
		System.out.println("获取key1的值：" + redisClientTemplate.get("key1"));
		System.out.println("将key2的值减去整数5：" + redisClientTemplate.decrBy("key2", 5));
		System.out.println("获取key2的值：" + redisClientTemplate.get("key2"));
	}

	/**
	 * 列表
	 */
	@Test
	public void testList() {
		System.out.println("===========添加一个list===========");
		redisClientTemplate.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap",
				"LinkedHashMap");
		redisClientTemplate.lpush("collections", "HashSet");
		redisClientTemplate.lpush("collections", "TreeSet");
		redisClientTemplate.lpush("collections", "TreeMap");

		// -1代表倒数第一个元素，-2代表倒数第二个元素
		System.out.println("collections的内容：" + redisClientTemplate.lrange("collections", 0, -1));
		System.out.println("collections区间0-3的元素：" + redisClientTemplate.lrange("collections", 0, 3));
		System.out.println("===============================");

		// 删除列表指定的值 ，第二个参数为删除的个数（有重复时），后add进去的值先被删，类似于出栈
		System.out.println("删除指定元素个数：" + redisClientTemplate.lrem("collections", 2, "HashMap"));
		System.out.println("collections的内容：" + redisClientTemplate.lrange("collections", 0, -1));
		System.out.println("截取下表0-3区间之外的元素：" + redisClientTemplate.ltrim("collections", 0, 3));
		System.out.println("collections的内容：" + redisClientTemplate.lrange("collections", 0, -1));
		System.out.println("collections列表出栈（左端）：" + redisClientTemplate.lpop("collections"));
		System.out.println("collections的内容：" + redisClientTemplate.lrange("collections", 0, -1));

		System.out.println("collections添加元素，从列表右端，与lpush相对应：" + redisClientTemplate.rpush("collections", "EnumMap"));
		System.out.println("collections的内容：" + redisClientTemplate.lrange("collections", 0, -1));
		System.out.println("collections列表出栈（右端）：" + redisClientTemplate.rpop("collections"));
		System.out.println("collections的内容：" + redisClientTemplate.lrange("collections", 0, -1));
		System.out.println("修改collections指定下标1的内容：" + redisClientTemplate.lset("collections", 1, "LinkedArrayList"));
		System.out.println("collections的内容：" + redisClientTemplate.lrange("collections", 0, -1));
		System.out.println("===============================");
		System.out.println("collections的长度：" + redisClientTemplate.llen("collections"));
		System.out.println("获取collections下标为2的元素：" + redisClientTemplate.lindex("collections", 2));
		System.out.println("===============================");
		redisClientTemplate.lpush("sortedList", "3", "6", "2", "0", "7", "4");
		System.out.println("sortedList排序前：" + redisClientTemplate.lrange("sortedList", 0, -1));
		System.out.println(redisClientTemplate.sort("sortedList"));
		System.out.println("sortedList排序后：" + redisClientTemplate.lrange("sortedList", 0, -1));
	}

	/**
	 * 集合
	 */
	@Test
	public void testSet() {
		System.out.println("============向集合中添加元素============");
		System.out.println(redisClientTemplate.sadd("eleSet", "e1", "e2", "e4", "e3", "e0", "e8", "e7", "e5"));
		System.out.println(redisClientTemplate.sadd("eleSet", "e6"));
		System.out.println(redisClientTemplate.sadd("eleSet", "e6"));
		System.out.println("eleSet的所有元素为：" + redisClientTemplate.smembers("eleSet"));
		System.out.println("删除一个元素e0：" + redisClientTemplate.srem("eleSet", "e0"));
		System.out.println("eleSet的所有元素为：" + redisClientTemplate.smembers("eleSet"));
		System.out.println("删除两个元素e7和e6：" + redisClientTemplate.srem("eleSet", "e7", "e6"));
		System.out.println("eleSet的所有元素为：" + redisClientTemplate.smembers("eleSet"));
		System.out.println("随机的移除集合中的一个元素：" + redisClientTemplate.spop("eleSet"));
		System.out.println("随机的移除集合中的一个元素：" + redisClientTemplate.spop("eleSet"));
		System.out.println("eleSet的所有元素为：" + redisClientTemplate.smembers("eleSet"));
		System.out.println("eleSet中包含元素的个数：" + redisClientTemplate.scard("eleSet"));
		System.out.println("e3是否在eleSet中：" + redisClientTemplate.sismember("eleSet", "e3"));
		System.out.println("e1是否在eleSet中：" + redisClientTemplate.sismember("eleSet", "e1"));
		System.out.println("e1是否在eleSet中：" + redisClientTemplate.sismember("eleSet", "e5"));

		System.out.println("=================================");
		Jedis jedis = redisClientTemplate.getOneJedis();
		System.out.println(jedis.sadd("eleSet1", "e1", "e2", "e4", "e3", "e0", "e8", "e7", "e5"));
		System.out.println(jedis.sadd("eleSet2", "e1", "e2", "e4", "e3", "e0", "e8"));
		System.out.println("将eleSet1中删除e1并存入eleSet3中：" + jedis.smove("eleSet1", "eleSet3", "e1"));
		System.out.println("eleSet1的所有元素为：" + jedis.smembers("eleSet1"));
		System.out.println("eleSet3的所有元素为：" + jedis.smembers("eleSet3"));
		System.out.println("将eleSet1中删除e2并存入eleSet3中：" + jedis.smove("eleSet1", "eleSet3", "e2"));
		System.out.println("eleSet1中的元素：" + jedis.smembers("eleSet1"));
		System.out.println("eleSet3中的元素：" + jedis.smembers("eleSet3"));
		System.out.println("============集合运算=================");
		System.out.println("eleSet1中的元素：" + jedis.smembers("eleSet1"));
		System.out.println("eleSet2中的元素：" + jedis.smembers("eleSet2"));
		System.out.println("eleSet1和eleSet2的交集:" + jedis.sinter("eleSet1", "eleSet2"));
		System.out.println("eleSet1和eleSet2的并集:" + jedis.sunion("eleSet1", "eleSet2"));
		// eleSet1中有，eleSet2中没有
		System.out.println("eleSet1和eleSet2的差集:" + jedis.sdiff("eleSet1", "eleSet2"));
		jedis.close();
	}

	/**
	 * 散列
	 */
	@Test
	public void testHash() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key6", "value6");
		map.put("key3", "value1");
		map.put("key2", "value2");
		map.put("key1", "value3");
		map.put("key4", "value4");
		redisClientTemplate.hmset("hash", map);
		redisClientTemplate.hset("hash", "key5", "value5");
		// return Map<String,String>
		System.out.println("散列hash的所有键值对为：" + redisClientTemplate.hgetAll("hash"));
		// return Set<String>
		System.out.println("散列hash的所有键为：" + redisClientTemplate.hkeys("hash"));
		// return List<String>
		System.out.println("散列hash的所有值为：" + redisClientTemplate.hvals("hash"));
		System.out.println("将key6保存的值加上一个整数，如果key6不存在则添加key6：" + redisClientTemplate.hincrBy("hash", "key6", 6));
		System.out.println("散列hash的所有键值对为：" + redisClientTemplate.hgetAll("hash"));
		System.out.println("将key6保存的值加上一个整数，如果key6不存在则添加key6：" + redisClientTemplate.hincrBy("hash", "key6", 3));
		System.out.println("散列hash的所有键值对为：" + redisClientTemplate.hgetAll("hash"));
		System.out.println("删除一个或者多个键值对：" + redisClientTemplate.hdel("hash", "key2"));
		System.out.println("散列hash的所有键值对为：" + redisClientTemplate.hgetAll("hash"));
		System.out.println("散列hash中键值对的个数：" + redisClientTemplate.hlen("hash"));
		System.out.println("判断hash中是否存在key2：" + redisClientTemplate.hexists("hash", "key2"));
		System.out.println("判断hash中是否存在key3：" + redisClientTemplate.hexists("hash", "key3"));
		System.out.println("获取hash中的值：" + redisClientTemplate.hmget("hash", "key3"));
		System.out.println("获取hash中的值：" + redisClientTemplate.hmget("hash", "key3", "key4"));
	}

	/**
	 * 有序集合
	 */
	@Test
	public void testSortSet() {
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("key2", 1.2);
		map.put("key3", 4.0);
		map.put("key4", 5.0);
		map.put("key5", 0.2);
		System.out.println(redisClientTemplate.zadd("zset", 3, "key1"));
		System.out.println(redisClientTemplate.zadd("zset", map));
		System.out.println("zset中的所有元素：" + redisClientTemplate.zrange("zset", 0, -1));
		System.out.println("zset中的所有元素：" + redisClientTemplate.zrangeWithScores("zset", 0, -1));
		// score范围是1~100的筛选出来，并展示key
		System.out.println("zset中的所有元素：" + redisClientTemplate.zrangeByScore("zset", 1, 100));
		// score范围是1~100的筛选出来，并展示key2和value
		System.out.println("zset中的所有元素：" + redisClientTemplate.zrangeByScoreWithScores("zset", 1, 100));
		System.out.println("zset中key2的分值：" + redisClientTemplate.zscore("zset", "key2"));
		// 排名是从0开始的
		System.out.println("zset中key2的排名：" + redisClientTemplate.zrank("zset", "key2"));
		System.out.println("删除zset中的元素key3：" + redisClientTemplate.zrem("zset", "key3"));
		System.out.println("zset中的所有元素：" + redisClientTemplate.zrange("zset", 0, -1));
		System.out.println("zset中元素的个数：" + redisClientTemplate.zcard("zset"));
		System.out.println("zset中分值在1-4之间的元素的个数：" + redisClientTemplate.zcount("zset", 1, 4));
		System.out.println("key2的分值加上5：" + redisClientTemplate.zincrby("zset", 5, "key2"));
		System.out.println("key3的分值加上4：" + redisClientTemplate.zincrby("zset", 4, "key3"));
		System.out.println("zset中的所有元素：" + redisClientTemplate.zrange("zset", 0, -1));
	}

	/**
	 * 排序sort
	 */
	@Test
	public void testSort() {
		redisClientTemplate.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap",
				"LinkedHashMap");
		System.out.println("collections的内容：" + redisClientTemplate.lrange("collections", 0, -1));
		SortingParams sortingParameters = new SortingParams();
		System.out.println("collections排序后"+redisClientTemplate.sort("collections", sortingParameters.alpha()));
		System.out.println("===============================");
		redisClientTemplate.lpush("sortedList", "3", "6", "2", "0", "7", "4");
		System.out.println("sortedList排序前：" + redisClientTemplate.lrange("sortedList", 0, -1));
		System.out.println("升序：" + redisClientTemplate.sort("sortedList", sortingParameters.asc()));
		System.out.println("降序：" + redisClientTemplate.sort("sortedList", sortingParameters.desc()));
		System.out.println("===============================");
		redisClientTemplate.lpush("userlist", "33");
		redisClientTemplate.lpush("userlist", "22");
		redisClientTemplate.lpush("userlist", "44");
		redisClientTemplate.lpush("userlist", "11");
		redisClientTemplate.lpush("userlist", "55");
		

		redisClientTemplate.hset("user:44", "name", "44");
		redisClientTemplate.hset("user:55", "name", "55");
		redisClientTemplate.hset("user:33", "name", "33");
		redisClientTemplate.hset("user:22", "name", "22");
		redisClientTemplate.hset("user:11", "name", "11");

		redisClientTemplate.hset("user:11", "add", "beijing");
		redisClientTemplate.hset("user:22", "add", "shanghai");
		redisClientTemplate.hset("user:33", "add", "guangzhou");
		redisClientTemplate.hset("user:55", "add", "chongqing");
		redisClientTemplate.hset("user:44", "add", "hangzhou");
		sortingParameters = new SortingParams();
		sortingParameters.get("user:*->name");
		sortingParameters.get("user:*->add");
		
	
		/*
		 * 根据userlist的值，排序得到11，22，33，55；
		 * 分别填充到sortingParameters.get("user:*->name")星号中，得到对应的值输出
		 */
		System.out.println(redisClientTemplate.sort("userlist", sortingParameters));
	}

}
