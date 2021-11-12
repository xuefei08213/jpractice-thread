package org.jpractice.thread.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public void read(){
        try{
            readLock.lock();
            System.out.println("当前线程："+Thread.currentThread().getName() + "read进入。。。");
            Thread.sleep(3000);
            System.out.println("当前线程"+Thread.currentThread().getName() + "read退出。。。");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }

    public void write(){
        try{
            writeLock.lock();
            System.out.println("当前线程："+Thread.currentThread().getName() + "write进入。。。");
            Thread.sleep(3000);
            System.out.println("当前线程"+Thread.currentThread().getName() + "write退出。。。");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
        Thread t1 = new Thread(()->{
            readWriteLockTest.read();
        },"t1");

        Thread t2 = new Thread(()->{
            readWriteLockTest.read();
        },"t2");

        Thread t3 = new Thread(()->{
            readWriteLockTest.write();
        },"t3");

        Thread t4 = new Thread(()->{
            readWriteLockTest.write();
        },"t4");

//        t1.start();
//        t2.start();
//
//        t1.start();
//        t3.start();

        t3.start();
        t4.start();

    }
}
