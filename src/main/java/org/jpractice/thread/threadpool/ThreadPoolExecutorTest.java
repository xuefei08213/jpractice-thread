package org.jpractice.thread.threadpool;

import java.util.concurrent.*;

/**
 * @description
 * @author: xuefei
 * @create 2021/11/13 20:16:10
 */
public class ThreadPoolExecutorTest {

	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExecutor =
				new ThreadPoolExecutor(
						1,
						4,
						30,
						TimeUnit.SECONDS,
						new LinkedBlockingDeque<>(1),
						new MyThreadFactory(true,"test"),
						new MyPolicy());
		for(int i = 0;i<10;i++){
			threadPoolExecutor.submit(new MyTask(i));
		}

		threadPoolExecutor.shutdown();

	}
}

class MyTask implements Runnable{

	int index = 0;

	public MyTask(int index){
		this.index = index;
	}

	@Override
	public void run() {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println(System.currentTimeMillis() +  "  " + Thread.currentThread().getName() + " >> run " + index);
	}
}

class MyPolicy implements RejectedExecutionHandler{

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println(System.currentTimeMillis() +  "  "+r + "被拒绝。。。");
	}
}

class MyThreadFactory implements ThreadFactory{

	private boolean saveLog;

	private String factoryName;

	public MyThreadFactory(boolean saveLog,String factoryName){
		this.saveLog = saveLog;
		this.factoryName = factoryName;
	}

	@Override
	public Thread newThread(Runnable r) {

		if(saveLog){
			System.out.println(System.currentTimeMillis() + " " +this.factoryName + " create start ");
		}

		Thread thread = new Thread(r);
		thread.setName("Thread-"+thread.getName()+":"+thread.getId());
		//
		/**
		 * 捕获线程里面的异常
		 * 但是使用submit方法会使setUncaughtExceptionHandler失效
		 */
		thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {

			}
		});
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if(saveLog){
			System.out.println(System.currentTimeMillis() + " " +this.factoryName + " create end ");
		}

		return thread;
	}
}
