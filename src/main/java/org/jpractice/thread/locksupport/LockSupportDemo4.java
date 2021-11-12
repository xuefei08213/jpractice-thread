package org.jpractice.thread.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @description
 * @author: xuefei
 * @create 2021/11/12 12:45:14
 */
public class LockSupportDemo4 {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " start run");
				// 对比传入blocker有什么区别
				LockSupport.park(this);
				System.out.println(Thread.currentThread().getName() + " stop run");
			}
		});

		thread.start();
		thread.join();
		System.out.println(Thread.currentThread().getName() + " stop run");
	}
}
