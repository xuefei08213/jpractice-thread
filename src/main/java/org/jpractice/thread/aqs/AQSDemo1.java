package org.jpractice.thread.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 * @author: xuefei
 * @create 2021/11/12 22:58:28
 */
public class AQSDemo1 {

	static ReentrantLock rl = new ReentrantLock();
	static Condition condition = rl.newCondition();

	public static void main(String[] args) throws InterruptedException {

		new Runner().start();
		new Runner().start();
		new Runner().start();
	}

	static class Runner extends Thread{
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				rl.lock();
				System.out.println(Thread.currentThread().getName()+" before await..");
				//condition.signal();
				condition.await();
				System.out.println(Thread.currentThread().getName()+" after await..");
				rl.unlock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
