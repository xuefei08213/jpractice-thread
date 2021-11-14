package org.jpractice.thread.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: xuefei
 * @create 2021/11/14 07:56:49
 */
public class CustomThreadPoolTest {

	public static void main(String[] args) {
		System.out.println("线程执行前后加入切面，线程池关闭后增加切面");
		CustomThreadPool executor = new CustomThreadPool(2, 2,
				Integer.MAX_VALUE, TimeUnit.SECONDS,
				new LinkedBlockingDeque<Runnable>());
		executor.execute(new Runner("A1"));
		executor.execute(new Runner("A2"));
		executor.execute(new Runner("A3"));
		executor.execute(new Runner("A4"));
		//shutdown之后terminated执行
		executor.shutdown();
	}

}

class CustomThreadPool extends ThreadPoolExecutor{

	public CustomThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		System.out.println("before " + ((Runner) r).getName() + " run");
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		System.out.println("after " + ((Runner) r).getName() + " run");
	}

	@Override
	protected void terminated() {
		super.terminated();
		System.out.println("ThreadPoolExecutor is terminated");
	}
}

class Runner implements Runnable {
	private String name;

	public Runner(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		System.out.println(name + " run " + System.currentTimeMillis());
	}
}
