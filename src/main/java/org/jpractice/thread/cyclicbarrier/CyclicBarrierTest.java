/**
 * 
 */
package org.jpractice.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2019-06-26 12:49:02
 * @Description: TODO
 * @version V1.0
 */
public class CyclicBarrierTest {

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread-1准备好le");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("thread-1开始执行");
        },"thread-1").start();

        new Thread(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread-2准备好le");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("thread-2开始执行");
        },"thread-2").start();

        cyclicBarrier.await();
        System.out.println("主线程执行了");
//        new Thread(()->{
//            System.out.println("thread-3准备好le");
//            try {
//                cyclicBarrier.await(2, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (BrokenBarrierException e) {
//                e.printStackTrace();
//            } catch (TimeoutException e) {
//                System.out.println("我要先跑了");
//                e.printStackTrace();
//            }
//            System.out.println("thread-3开始执行");
//        },"thread-3").start();


    }

}
