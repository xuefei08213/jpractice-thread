package org.jpractice.thread.locksupport;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo2 {

	public static void main(String[] args) {
		// 先调用unpark再调用park会导致park失效，只会影响第一次park的调用起作用
		System.out.println(Thread.currentThread().getName()+ " start run ");

		LockSupport.unpark(Thread.currentThread());
		LockSupport.unpark(Thread.currentThread());
		LockSupport.unpark(Thread.currentThread());
		LockSupport.unpark(Thread.currentThread());

		LockSupport.park();
		System.out.println(Thread.currentThread().getName()+" park1 already run");

		LockSupport.park();
		System.out.println(Thread.currentThread().getName()+" park2 already run");

		LockSupport.park();
		System.out.println(Thread.currentThread().getName()+" park3 already run");

		LockSupport.park();
		System.out.println(Thread.currentThread().getName()+" park4 already run");


	}
}
