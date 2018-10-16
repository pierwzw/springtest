package com.pier.cache;

import com.pier.aware.ApplicationContextHolder;
import org.apache.ibatis.cache.Cache;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @auther zhongweiwu
 * @date 2018/10/15 10:42
 */
public class RedisCache2 implements Cache {

    private static Logger LOGGER = LogManager.getLogger(RedisCache2.class);

    private final String id;

    /* 这里不能通过autowire的方式引用redisTemplate，因为RedisCache并不是Spring容器里的bean */
    private static RedisTemplate<String, Object> redisTemplate = ApplicationContextHolder.getBean("redisTemplate");;

    public static void setTemplate(RedisTemplate<String, Object> template) {
        RedisCache2.redisTemplate = template;
    }

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public RedisCache2(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void putObject(Object key, Object value) {
        redisTemplate.opsForValue().set(key.toString(), value, 10, TimeUnit.MINUTES);
    }

    public Object getObject(Object key) {
        return redisTemplate.opsForValue().get(key.toString());
    }

    public Object removeObject(Object key) {
        redisTemplate.opsForValue().set(key.toString(), "", 0, TimeUnit.MINUTES);
        return key;
    }

    public void clear() {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

    public int getSize() {
        return redisTemplate.getConnectionFactory().getConnection().dbSize().intValue();
    }

    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
