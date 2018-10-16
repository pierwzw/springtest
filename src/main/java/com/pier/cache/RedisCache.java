/*
package com.pier.cache;

import org.apache.ibatis.cache.Cache;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import org.apache.ibatis.cache.Cache;
import org.mybatis.caches.redis.RedisCallback;
import org.mybatis.caches.redis.
import org.mybatis.caches.redis.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

*/
/**
 * @auther zhongweiwu
 * @date 2018/10/15 10:24
 *//*

public class RedisCache implements Cache {

    private final ReadWriteLock readWriteLock = new DummyReadWriteLock();
    private String id;
    private static JedisPool pool;

    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        } else {
            this.id = id;
            RedisConfig redisConfig = RedisConfiguration.getInstance().parseConfiguration();
            pool = new JedisPool(redisConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getConnectionTimeout(), redisConfig.getSoTimeout(), redisConfig.getPassword(), redisConfig.getDatabase(), redisConfig.getClientName());
        }
    }

    private Object execute(RedisCallback callback) {
        Jedis jedis = pool.getResource();

        Object var3;
        try {
            var3 = callback.doWithRedis(jedis);
        } finally {
            jedis.close();
        }

        return var3;
    }

    public String getId() {
        return this.id;
    }

    public int getSize() {
        return ((Integer)this.execute(new RedisCallback() {
            public Object doWithRedis(Jedis jedis) {
                Map<byte[], byte[]> result = jedis.hgetAll(RedisCache.this.id.toString().getBytes());
                return result.size();
            }
        })).intValue();
    }

    public void putObject(final Object key, final Object value) {
        this.execute(new RedisCallback() {
            public Object doWithRedis(Jedis jedis) {
                jedis.hset(RedisCache.this.id.toString().getBytes(), key.toString().getBytes(), SerializeUtil.serialize(value));
                return null;
            }
        });
    }

    public Object getObject(final Object key) {
        return this.execute(new RedisCallback() {
            public Object doWithRedis(Jedis jedis) {
                return SerializeUtil.unserialize(jedis.hget(RedisCache.this.id.toString().getBytes(), key.toString().getBytes()));
            }
        });
    }

    public Object removeObject(final Object key) {
        return this.execute(new RedisCallback() {
            public Object doWithRedis(Jedis jedis) {
                return jedis.hdel(RedisCache.this.id.toString(), new String[]{key.toString()});
            }
        });
    }

    public void clear() {
        this.execute(new RedisCallback() {
            public Object doWithRedis(Jedis jedis) {
                jedis.del(RedisCache.this.id.toString());
                return null;
            }
        });
    }

    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    @Override
    public String toString() {
        return "Redis {" + this.id + "}";
    }
}
*/
