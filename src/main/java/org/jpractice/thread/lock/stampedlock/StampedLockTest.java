package org.jpractice.thread.lock.stampedlock;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {

    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();

        long stamp = stampedLock.writeLock();
        System.out.println("get stamped lock"+ stamp);

        stampedLock.unlock(stamp);

        stamp = stampedLock.writeLock();
        System.out.println("get stamped lock"+ stamp);
        stampedLock.unlock(stamp);

    }
}
