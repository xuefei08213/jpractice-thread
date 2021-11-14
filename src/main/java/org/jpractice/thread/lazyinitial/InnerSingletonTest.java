package org.jpractice.thread.lazyinitial;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description
 * @author: xuefei
 * @create 2021/11/14 09:40:21
 */
public class InnerSingletonTest {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for(int i = 0;i<10;i++){
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Singleton.getInstance());
				}
			});
		}
		executorService.shutdown();
	}

}

/**
 * 静态内部类只有在第一次被使用时才会被初始化
 */
class Singleton{
	private static class InnerSingleton{
		private static Singleton singleton = new Singleton();
	}

	private Singleton(){
		try {
			Thread.sleep(1000);
			System.out.println("创建对象耗时较长");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static Singleton getInstance(){
		return InnerSingleton.singleton;
	}

	@Override
	public String toString() {
		return this.hashCode() + "";
	}
}
