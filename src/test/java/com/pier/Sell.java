package com.pier;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.通过两个线程模拟售票点出售火车票，A售票点每隔5秒出售一张票，B售票点每隔20秒出售一张票总票数50，
 * 5分钟后，主线程汇总每个子线程的售票情况
 * @auther zhongweiwu
 * @date 2019/3/26 20:31
 */
public class Sell extends Thread {

    private static final int count=50;
    private final long period = 5 * 60 * 1000;
    private static AtomicInteger k = new AtomicInteger(0);
    private String pot;
    private int time;
    private long start;

    public Sell(String pot, int time, long start){
        this.pot = pot;
        this.time = time;
        this.start = start;
    }

    public void run(){
        while(sell(pot) && System.currentTimeMillis() - start <= period){
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean sell(String pot){
        if(k.get()<count){
            System.out.println(pot+"售票点售出第"+k.incrementAndGet()+"张票");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread t1 = new Sell("A", 5000, start);
        Thread t2 = new Sell("B", 20000, start);
        t1.start();
        t2.start();
    }
}
