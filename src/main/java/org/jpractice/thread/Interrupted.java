/**
 * 
 */
package org.jpractice.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-12-23 11:42:17
 * @Description: TODO
 * @version V1.0
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);

        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        TimeUnit.SECONDS.sleep(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is" + busyThread.isInterrupted());

        TimeUnit.SECONDS.sleep(2);

    }

    static class SleepRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {

            }
        }

    }
}
