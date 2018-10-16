package com.pier.cache;

import org.apache.ibatis.cache.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @auther zhongweiwu
 * @date 2018/10/15 10:20
 */
public class MyCache implements Cache {

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Map<Object,Object> cache = new ConcurrentHashMap<Object, Object>();
    private String id;

    public  MyCache(){

    }

    //必须有该构造函数
    public MyCache(String id){
        this.id = id;
    }

    // 获取缓存编号
    public String getId() {
        System.out.println("得到ID：" + id);
        return id;
    }

    //获取缓存对象的大小
    public int getSize() {
        return cache.size();
    }
    // 保存key值缓存对象
    public void putObject(Object key, Object value) {
        System.out.println("往缓存中添加元素：key=" + key+",value=" + value);
        cache.put(key,value);
    }

    //通过KEY
    public Object getObject(Object key) {
        return cache.get(key);
    }

    // 通过key删除缓存对象
    public Object removeObject(Object key) {
        System.out.println("移除缓存对象：" + key);
        return cache.remove(key);
    }

    // 清空缓存
    public void clear() {
        cache.clear();
    }

    // 获取缓存的读写锁
    public ReadWriteLock getReadWriteLock() {
        return lock;
    }
}
