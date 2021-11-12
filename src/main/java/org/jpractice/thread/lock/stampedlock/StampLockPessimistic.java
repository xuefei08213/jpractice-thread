package org.jpractice.thread.lock.stampedlock;

import java.util.concurrent.locks.StampedLock;

public class StampLockPessimistic {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("StampedLock.readLock正常使用：注意每次获得的写锁stamp不同，必须所有的读锁都释放才可以获得写锁");

        StampedLock stampedLock = new StampedLock();
        long stamp1 = stampedLock.readLock();
        System.out.println("get read lock1,stamp="+stamp1);

        long stamp2 = stampedLock.readLock();
        System.out.println("get read lock2,stamp="+stamp2);

        stampedLock.unlock(stamp1);
        stampedLock.unlock(stamp2);

        long stamp3 = stampedLock.writeLock();
        System.out.println("get write lock2,stamp="+stamp3);


        System.out.println("StampedLock.readLock正常使用：获得读锁->释放->获得写锁");
        StampedLock stampedLock1 = new StampedLock();
        long stamp1_1 = stampedLock1.readLock();
        System.out.println(Thread.currentThread().getName()+" get read lock1,stamp="+stamp1_1);
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " run");
            long stamp1_2 = stampedLock1.writeLock();
            System.out.println(Thread.currentThread().getName()+" get write lock1,stamp="+stamp1_2);
        },"tl1").start();

        Thread.sleep(3000);
        stampedLock1.unlockRead(stamp1_1);

        System.out.println(Thread.currentThread().getName()+" release read lock1,stamp="+stamp1_1);
    }
}
