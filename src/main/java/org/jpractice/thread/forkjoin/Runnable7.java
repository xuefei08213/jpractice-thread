package org.jpractice.thread.forkjoin;

public class Runnable7 implements Runnable {
    public void run() {
        try {
            System.out.println("begin " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(4000);
            System.out.println(" end " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}