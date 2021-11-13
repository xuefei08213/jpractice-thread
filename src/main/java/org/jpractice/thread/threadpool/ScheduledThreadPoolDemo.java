package org.jpractice.thread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: xuefei
 * @create 2021/11/13 19:28:45
 */
public class ScheduledThreadPoolDemo {

	public static void test1(){
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		for(int i = 0;i<10;i++){
			final int index = i;
			Runnable task = new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ">> delay " + index + "seconds run ....");
				}
			};
			scheduledExecutorService.schedule(task,i, TimeUnit.SECONDS);
		}
		scheduledExecutorService.shutdown();
	}

	public static void test2(){
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(12);
		Runnable task = new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + ">> sleep " + System.currentTimeMillis());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ">> run " + System.currentTimeMillis());
			}
		};
		ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(task,0,1,TimeUnit.SECONDS);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		scheduledFuture.cancel(true);
		scheduledExecutorService.shutdown();
	}

	public static void test3(){
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(12);
		Runnable task = new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + ">> sleep " + System.currentTimeMillis());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ">> run " + System.currentTimeMillis());
			}
		};
		ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(task,0,1,TimeUnit.SECONDS);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		scheduledFuture.cancel(true);
		scheduledExecutorService.shutdown();
	}

	public static void main(String[] args) {
//		test1();
		test2();
//		test3();
	}
}
