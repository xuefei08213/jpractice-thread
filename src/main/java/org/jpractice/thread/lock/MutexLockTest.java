/**
 * 
 */
package org.jpractice.thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2019-07-19 11:58:27
 * @Description: TODO
 * @version V1.0
 */
public class MutexLockTest {

    private Mutex mutex = new Mutex();

    public void doSomething() {

        // try {
        // mutex.lockInterruptibly();
        // } catch (InterruptedException e1) {
        // e1.printStackTrace();
        // }
        mutex.lock();

        try {
            TimeUnit.MINUTES.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mutex.unlock();
    }

    public static void main(String[] args) {

        MutexLockTest lockTest = new MutexLockTest();
        new Thread(() -> {
            lockTest.doSomething();
        }, "one").start();

        Thread two = new Thread(() -> {
            lockTest.doSomething();
        }, "two");
        
        two.start();
        
        // two.interrupt();

        // try {
        // TimeUnit.SECONDS.sleep(10);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        //
        // two.interrupt();

        // for (int i = 0; i < 2; i++) {
        //
        //
        //
        // new Thread(() -> {
        // lockTest.doSomething();
        // }).start();
        //
        // }
    }

}
