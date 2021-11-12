/**
 * 
 */
package org.jpractice.thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2019-06-27 12:39:39
 * @Description: TODO
 * @version V1.0
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 5;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {

        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 尝试获取许可证");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 获取到许可证");
                    System.out.println("save data");
                    Thread.sleep(1000);
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + " 释放许可证");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        threadPool.shutdown();
    }

}
