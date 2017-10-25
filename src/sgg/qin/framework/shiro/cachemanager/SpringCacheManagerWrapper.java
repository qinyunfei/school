/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package sgg.qin.framework.shiro.cachemanager;

import net.sf.ehcache.Ehcache;
import sgg.qin.framework.redis.RedisCache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.*;

/**
 * 自定义缓存管理器
 * <p>User: Zhang Kaitao
 * <p>Date: 13-3-23 上午8:26
 * <p>Version: 1.0
 */
public class SpringCacheManagerWrapper implements CacheManager {

    private org.springframework.cache.CacheManager cacheManager;

    /**
     * 设置spring cache manager
     *
     * @param cacheManager
     */
    public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
    //根据缓存名字获取一个Cache  
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        org.springframework.cache.Cache springCache = cacheManager.getCache(name);
        return new SpringCacheWrapper(springCache);
    }

    static class SpringCacheWrapper implements Cache {
        private org.springframework.cache.Cache springCache;

        SpringCacheWrapper(org.springframework.cache.Cache springCache) {
            this.springCache = springCache;
        }
        //根据Key获取缓存中的值  
        @Override
        public Object get(Object key) throws CacheException {
            Object value = springCache.get(key);
            if (value instanceof SimpleValueWrapper) {
                return ((SimpleValueWrapper) value).get();
            }
            return value;
        }
      //往缓存中放入key-value，返回缓存中之前的值 
        @Override
        public Object put(Object key, Object value) throws CacheException {
            springCache.put(key, value);
            return value;
        }
      //移除缓存中key对应的值，返回该值
        @Override
        public Object remove(Object key) throws CacheException {
            springCache.evict(key);
            return null;
        }
      //清空整个缓存
        @Override
        public void clear() throws CacheException {
            springCache.clear();
        }
      //返回缓存大小  
        @Override
        public int size() {
            if(springCache.getNativeCache() instanceof RedisCache) {
            	RedisCache ehcache = (RedisCache) springCache.getNativeCache();
                return ehcache.size();
            }
            throw new UnsupportedOperationException("invoke spring cache abstract size method not supported");
        }
      //获取缓存中所有的key  
        @Override
        public Set keys() {
            if(springCache.getNativeCache() instanceof RedisCache) {
            	RedisCache cache = (RedisCache) springCache.getNativeCache();
                return cache.keys();
            }
            throw new UnsupportedOperationException("invoke spring cache abstract keys method not supported");
        }
        //获取缓存中所有的value  
        @Override
        public Collection values() {
            if(springCache.getNativeCache() instanceof RedisCache) {
            	RedisCache cache = (RedisCache) springCache.getNativeCache();
            	return cache.values();
            }
            throw new UnsupportedOperationException("invoke spring cache abstract values method not supported");
        }
    }
}
