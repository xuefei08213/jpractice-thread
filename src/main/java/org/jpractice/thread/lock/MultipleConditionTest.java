package org.jpractice.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultipleConditionTest {

    private ReentrantLock lock = new ReentrantLock();

    private Condition firstCondition =  lock.newCondition();

    private Condition secondCondition = lock.newCondition();

    public void firstRun(){
        try{
            lock.lock();
            System.out.println("当前线程："+Thread.currentThread().getName() + "进入firstRun等待");
            firstCondition.await();
            System.out.println("当前线程："+Thread.currentThread().getName() + "方法firstRun继续执行");
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }

    public void secondRun(){
        try{
            lock.lock();
            System.out.println("当前线程："+Thread.currentThread().getName() + "进入secondRun等待");
            firstCondition.await();
            System.out.println("当前线程："+Thread.currentThread().getName() + "方法secondRun继续执行");
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }

    public void thirdRun(){
        try{
            lock.lock();
            System.out.println("当前线程："+Thread.currentThread().getName() + "进入thirdRun等待");
            secondCondition.await();
            System.out.println("当前线程："+Thread.currentThread().getName() + "方法thirdRun继续执行");
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }

    public void forthRun(){
        try{
            lock.lock();
            System.out.println("当前线程："+Thread.currentThread().getName() + "forthRun唤醒");
            firstCondition.signalAll();
            System.out.println("当前线程："+Thread.currentThread().getName() + "方法forthRun继续执行");
        }finally {
            lock.unlock();
        }
    }

    public void fifthRun(){
        try{
            lock.lock();
            System.out.println("当前线程："+Thread.currentThread().getName() + "fifthRun唤醒");
            secondCondition.signal();
            System.out.println("当前线程："+Thread.currentThread().getName() + "方法fifthRun继续执行");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final MultipleConditionTest multipleConditionTest = new MultipleConditionTest();
        Thread first = new Thread(()->{
            multipleConditionTest.firstRun();
        },"first");

        Thread second = new Thread(()->{
            multipleConditionTest.secondRun();
        },"second");

        Thread third = new Thread(()->{
            multipleConditionTest.thirdRun();
        },"third");

        Thread forth = new Thread(()->{
            multipleConditionTest.forthRun();
        },"forth");

        Thread fifth = new Thread(()->{
            multipleConditionTest.fifthRun();
        },"fifth");

        first.start();
        second.start();
        third.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        forth.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fifth.start();

    }
}
