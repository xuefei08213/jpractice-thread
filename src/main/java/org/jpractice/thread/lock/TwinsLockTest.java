/**
 * 
 */
package org.jpractice.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.junit.Test;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2019-07-28 08:46:13
 * @Description: TODO
 * @version V1.0
 */
public class TwinsLockTest {

    Lock lock = new TwinsLock();

    public void doSomething() {
        lock.lock();
        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public void sleepSeconds() {
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        TwinsLockTest twinsLockTest = new TwinsLockTest();
        new Thread(() -> {
            twinsLockTest.sleepSeconds();
        }, "one").start();

        new Thread(() -> {
            twinsLockTest.doSomething();
        }, "three").start();

        Thread two = new Thread(() -> {
            twinsLockTest.doSomething();
        }, "two");

        two.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                lock.lock();
                try {
                    TimeUnit.MINUTES.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Worker worker = new Worker();
        worker.start();

        Worker worker2 = new Worker();
        worker2.start();
    }

}
