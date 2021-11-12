package org.jpractice.thread.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @description
 * @author: xuefei
 * @create 2021/11/11 23:24:18
 */
public class LockSupportDemo3 {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(()->{
			System.out.println(Thread.currentThread().getName()+" start run");
			// 调用线程的interrupt会导致park中断，但区别是不会抛出InterruptedException异常
			// 会对线程中所有的park生效
			LockSupport.park();
			// 通常通过判断来弥补不抛出InterruptedException异常
			if(Thread.currentThread().isInterrupted()){
				System.out.println(Thread.currentThread().getName()+" isInterrupted,so do something");
			}
			LockSupport.park();
			System.out.println(Thread.currentThread().getName()+" parked");
			LockSupport.park();
			System.out.println(Thread.currentThread().getName()+" parked");

			System.out.println(Thread.currentThread().getName()+" stop run");
		});

		t.start();
		Thread.sleep(2000);

		t.interrupt();
		t.join();
		System.out.println(Thread.currentThread().getName()+" stop run");

	}
}
