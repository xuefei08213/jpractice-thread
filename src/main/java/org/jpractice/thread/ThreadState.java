/**
 * 
 */
package org.jpractice.thread;

import org.jpractice.thread.ThreadState.Wating.Blocked;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-12-23 09:35:43
 * @Description: TODO
 * @version V1.0
 */
public class ThreadState {

    public static void main(String[] args) {
        new Thread(new TimeWating(), "TimeWaitingThread").start();
        new Thread(new Wating(), "WaitingThread").start();
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }

    static class TimeWating implements Runnable {

        @Override
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }

    static class Wating implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (Wating.class) {
                    try {
                        Wating.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        static class Blocked implements Runnable {

            @Override
            public void run() {
                synchronized (Blocked.class) {
                    SleepUtils.second(100);
                }
            }
        }

    }

}

