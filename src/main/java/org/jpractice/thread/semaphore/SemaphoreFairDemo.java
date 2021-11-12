package org.jpractice.thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreFairDemo {

    private static final int THREAD_COUNT = 5;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    // 强制公平获取锁
    private static Semaphore semaphore = new Semaphore(2,true);

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
