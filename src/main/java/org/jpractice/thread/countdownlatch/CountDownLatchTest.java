/**
 * 
 */
package org.jpractice.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2019-06-26 12:29:58
 * @Description: TODO
 * @version V1.0
 */
public class CountDownLatchTest {

    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread-1 countdown 减1");
                countDownLatch.countDown();
            }
        },"thread-1").start();

        new Thread(()->{
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread-2 countdown 减1");
            countDownLatch.countDown();

        },"thread-2").start();

        new Thread(()->{
            System.out.println("等待countdown变为0");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("countDown结束,thread-3执行");

        },"thread-3").start();

    }

}
