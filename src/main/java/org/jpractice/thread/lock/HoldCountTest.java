package org.jpractice.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class HoldCountTest {

    private ReentrantLock reentrantLock = new ReentrantLock();

    public void firstRun(){
        try{
            reentrantLock.lock();
            System.out.println("进入firstRun方法，holdCount为："+reentrantLock.getHoldCount());
            secondRun();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public void secondRun(){
        try{
            reentrantLock.lock();
            System.out.println("进入secondRun方法，holdCount为："+reentrantLock.getHoldCount());
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        HoldCountTest holdCountTest = new HoldCountTest();
        holdCountTest.firstRun();
    }
}
