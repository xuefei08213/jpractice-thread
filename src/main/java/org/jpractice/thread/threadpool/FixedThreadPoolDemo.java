package org.jpractice.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description
 * @author: xuefei
 * @create 2021/11/13 10:36:00
 */
public class FixedThreadPoolDemo {

	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		for(int i = 0;i<10;i++){
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			int index  = i;
			Runnable task = new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ">>"+index);
				}
			};
			executorService.execute(task);
		}

		executorService.shutdown();
	}
}
