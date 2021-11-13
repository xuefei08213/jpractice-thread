package org.jpractice.thread.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo2 {
    public static void main(String[] args) throws InterruptedException {

        Runner runner = new Runner();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(runner);
        executorService.submit(runner);
        executorService.submit(runner);
//        executorService.shutdown();
        executorService.shutdownNow();
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
        Thread.sleep(1000);
        System.out.println(executorService.isTerminated());
        Thread.sleep(4000);
        System.out.println(executorService.isTerminated());
        System.out.println("main is over");

//        Runner runner = new Runner();
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        executorService.submit(runner);
//        executorService.submit(runner);
//        executorService.submit(runner);
//        executorService.shutdown();
//        //executorService.shutdownNow();
//        System.out.println(executorService.awaitTermination(1, TimeUnit.SECONDS));
//        System.out.println(executorService.awaitTermination(1, TimeUnit.SECONDS));
//        System.out.println(executorService.awaitTermination(1, TimeUnit.SECONDS));
//        System.out.println(executorService.awaitTermination(1, TimeUnit.SECONDS));
//        System.out.println(executorService.awaitTermination(1, TimeUnit.SECONDS));
//        System.out.println("main is over");


    }

    static class Runner implements Runnable {
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " begin");
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName() + " end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //不做特殊处理的任务，无法规避shutdownNow的问题
    static class Runner1 implements Runnable {
        public void run() {
            while(true){
                System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis());
            }
        }
    }

    //判断线程是否Interrupted的程序，规避shutdownNow的问题
    static class Runner2 implements Runnable {
        public void run() {
            while(true){
                System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis());
                if(Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + " interrupted");
                    break;
                }
            }
        }
    }
}