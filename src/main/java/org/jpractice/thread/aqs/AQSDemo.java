package org.jpractice.thread.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 * @author: xuefei
 * @create 2021/11/12 22:47:40
 */
public class AQSDemo {

	public static void main(String[] args) throws InterruptedException {
		ReentrantLock rl = new ReentrantLock();
		System.out.println(Thread.currentThread().getName()+" start rl.lock");
		rl.lock();
		rl.lock();

		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+" start rl.lock");
				System.out.println("start re lock2");
				rl.lock();
			}
		});

		th.start();
		th.join();
		rl.unlock();
		System.out.println(Thread.currentThread().getName()+" start rl.unlock");
		System.out.println("main is over");
	}
}
