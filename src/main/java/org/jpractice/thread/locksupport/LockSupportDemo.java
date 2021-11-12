package org.jpractice.thread.locksupport;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

	public static void main(String[] args) throws InterruptedException {
		parkAndUnpark();
		parkUntil();
		parkNanos();
	}

	public static void  parkAndUnpark() throws InterruptedException {
		Thread thread = new Thread(()->{
			System.out.println(Thread.currentThread().getName()+" start run ");
			// park与unpark需要成对使用
			LockSupport.park();

			System.out.println(Thread.currentThread().getName() + " stop run ");
		});
		thread.start();
		Thread.sleep(2000);
		LockSupport.unpark(thread);

		// 保证线程thread执行完成之后再执行主线程的
		thread.join();
		System.out.println(Thread.currentThread().getName() + " stop run ");
	}

	public static void parkUntil(){
		Thread thread = new Thread(()->{
			System.out.println(Thread.currentThread().getName()+" start run ");
			// parkUntil可以单独使用，堵塞直到什么时候
			LockSupport.parkUntil(System.currentTimeMillis()+2000);

			System.out.println(Thread.currentThread().getName() + " stop run ");
		},"parkUntil");
		thread.start();
	}

	public static void parkNanos(){
		Thread thread = new Thread(()->{
			System.out.println(Thread.currentThread().getName()+" start run ");
			// 通过纳秒控制
			LockSupport.parkNanos(1000000000);

			System.out.println(Thread.currentThread().getName() + " stop run ");
		},"parkNanos");
		thread.start();
	}
}
