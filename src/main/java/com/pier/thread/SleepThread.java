package com.pier.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther zhongweiwu
 * @date 2018/11/26 20:08
 */
public class SleepThread extends Thread{
    @Override
    public void run(){
        while(true){
            try{
                SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
                System.out.println(sim.format(new Date()));
                // Thread类中只有sleep和join方法会抛出InterruptedException
                sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println("线程中断");
                return;
            }
        }
    }
}