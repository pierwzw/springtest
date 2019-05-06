package com.pier;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @auther zhongweiwu
 * @date 2019/1/22 16:50
 */
public class TestLock {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static Lock readLock = lock.readLock();
    private static Lock writeLock = lock.writeLock();

    static class ReadTask implements Runnable{
        private String name;
        public ReadTask(String name){
            this.name = name;
        }

        public void run() {
            readLock.lock();
        }
    }

    static class ReadWriteTask implements Runnable{
        private String name;
        public ReadWriteTask(String name){
            this.name = name;
        }

        public void run() {
            readLock.lock();
            writeLock.lock();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ReadTask("Thread-1"));
        Thread t2 = new Thread(new ReadTask("Thread-2"));
        Thread t3 = new Thread(new ReadWriteTask("Thread-3"));
        t1.setDaemon(false);
        t2.setDaemon(false);
        t3.setDaemon(false);
        t1.start();
        t2.start();
        t3.start();

    }
}
