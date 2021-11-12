package org.jpractice.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private Lock lock = new ReentrantLock();

    public void run1(){
        try{
            lock.lock();
            System.out.println("当前线程："+Thread.currentThread().getName() + "进入run1");
            Thread.sleep(1000);
            System.out.println("当前线程："+Thread.currentThread().getName() + "退出run1");
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }

    public void run2(){
        try{
            lock.lock();
            System.out.println("当前线程："+Thread.currentThread().getName() + "进入run2");
            Thread.sleep(2000);
            System.out.println("当前线程："+Thread.currentThread().getName() + "退出run2");
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        Thread thread1 = new Thread(()->{
            reentrantLockTest.run1();
        });
        Thread thread2 = new Thread(()->{
            reentrantLockTest.run2();
        });

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
