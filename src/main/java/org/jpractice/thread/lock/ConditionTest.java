package org.jpractice.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void run1(){
        try{
            lock.lock();
            Thread.sleep(3000);
            System.out.println("当前线程："+Thread.currentThread().getName() + "进入等待状态。。。");
            condition.await();
            System.out.println("当前线程："+Thread.currentThread().getName() + "继续执行");
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }

    public void run2(){
        try{
            lock.lock();
            System.out.println("当前线程："+Thread.currentThread().getName() + "进入run2");
            Thread.sleep(3000);
            System.out.println("当前线程："+Thread.currentThread().getName() + "发出唤醒");
            condition.signal();
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ConditionTest conditionTest = new ConditionTest();
        Thread thread1 = new Thread(()->{
            conditionTest.run1();
        });
        Thread thread2 = new Thread(()->{
            conditionTest.run2();
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
