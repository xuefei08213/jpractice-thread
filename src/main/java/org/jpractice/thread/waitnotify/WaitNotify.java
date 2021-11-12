/**
 * 
 */
package org.jpractice.thread.waitnotify;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-12-26 06:32:41
 * @Description: TODO
 * @version V1.0
 */
public class WaitNotify {

    static boolean flag = true;

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "notifyThread");
        notifyThread.setPriority(2);
        notifyThread.start();
    }

    static class Wait implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + "flag is true. wait"
                                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    System.out.println(Thread.currentThread() + "flag is false. running"
                            + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                }
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "hold lock. notify"
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                // SleepUtils.second(5);
            }
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "hold lock again. sleep"
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                // SleepUtils.second(5);
            }
        }

    }

}
