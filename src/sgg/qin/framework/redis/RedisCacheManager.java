package sgg.qin.framework.redis;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class RedisCacheManager implements CacheManager{
	
	//ShardedJedis工具类
	private RedisClientTemplate redisClientTemplate;
	

	public void setRedisClientTemplate(RedisClientTemplate redisClientTemplate) {
		this.redisClientTemplate = redisClientTemplate;
	}
	

	@Override
	public Cache getCache(String name) {
		// TODO Auto-generated method stub
		return new RedisCache(name, redisClientTemplate);
	}

	@Override
	public Collection<String> getCacheNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
