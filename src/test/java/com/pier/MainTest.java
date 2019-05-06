package com.pier;

/**
 * @auther zhongweiwu
 * @date 2018/11/20 17:24
 */

import com.pier.thread.ThreadPool;
import com.pier.util.CheckDuplicate;
import org.junit.Test;
import com.pier.thread.SleepThread;

import java.math.BigInteger;

public class MainTest {

    @Test
    public void test(){
        Integer a=1;
        Integer b=2;
        Integer c=3;
        Integer d=3;
        Integer e=321;
        Integer f=321;
        Long g=3l;
        System.out.println(c==d);
        System.out.println(e==f);
        System.out.println(c==(a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g==(a+b));
        System.out.println(g.equals(a+b));
    }

    /**
     * I imagine:
     * false
     * false
     * true
     * true
     * true
     * true
     * actual：
     * true
     * false
     * true
     * true
     * true
     * false
     */

    @Test
    public void sleepInterruptTest(){
        SleepThread t1 = new SleepThread();
        t1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        t1.interrupt();//主动打断线程，使SleepThread线程抛出异常
    }

    @Test
    public void testThredad(){
        //Thread[] thread = new Thread[15];
        for (int i=0; i<15; i++){
            ThreadPool.execute(new Runnable(){
                public void run() {
                    System.out.println("test thread run");
                }
            });
        }
        try {
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProcessorNum(){
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void testDuplicate(){
        String path = String.class.getName().replace('.', '/') + ".class";
        CheckDuplicate.checkDuplicate("constant.properties", false);
    }

}

