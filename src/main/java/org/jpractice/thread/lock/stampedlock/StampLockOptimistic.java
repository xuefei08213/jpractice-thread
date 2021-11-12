package org.jpractice.thread.lock.stampedlock;

import java.util.concurrent.locks.StampedLock;

public class StampLockOptimistic {

    public static void main(String[] args) throws InterruptedException {
        StampedLock stampedLock = new StampedLock();

        StampedLock stampedLock2 = new StampedLock();

        long stamp = stampedLock.tryOptimisticRead();
        System.out.println(Thread.currentThread().getName() + " tryOptimisticRead stamp = " + stamp);

        long stamp2 = stampedLock2.tryOptimisticRead();
        System.out.println(Thread.currentThread().getName() + " tryOptimisticRead stamp2 = " + stamp);

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " run");
            long stampForWriteLock = stampedLock.writeLock();
            System.out.println(Thread.currentThread().getName() + " get write lock, stamp = " + stampForWriteLock);
            stampedLock.unlock(stampForWriteLock);
            System.out.println(Thread.currentThread().getName() + " get write unlock, stamp = " + stampForWriteLock);

            long stampForReadLock = stampedLock2.readLock();
            stampedLock2.unlock(stampForReadLock);

        }).start();

        Thread.sleep(3000);
        boolean validate = stampedLock.validate(stamp);
        System.out.println(Thread.currentThread().getName() + " tryOptimisticRead validate="+validate);
        stamp = stampedLock.tryOptimisticRead();
        validate = stampedLock.validate(stamp);
        System.out.println(Thread.currentThread().getName() + " tryOptimisticRead validate="+validate);

        // 因为stampedLock2在子线程中调用的是读锁，对数据没影响，所以validate返回true
        System.out.println(Thread.currentThread().getName() + " tryOptimisticRead validate="+stampedLock2.validate(stamp2));



    }
}
