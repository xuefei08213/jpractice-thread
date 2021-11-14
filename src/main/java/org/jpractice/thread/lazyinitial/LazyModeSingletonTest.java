package org.jpractice.thread.lazyinitial;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description
 * @author: xuefei
 * @create 2021/11/14 09:23:06
 */
public class LazyModeSingletonTest {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for(int i = 0;i<10;i++){
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(LazyModeSingleton.getInstance());
				}
			});
		}
		executorService.shutdown();
	}


}

class LazyModeSingleton{

	private static LazyModeSingleton lazyModeSingleton = null;

	private LazyModeSingleton(){
		System.out.println("构建对象时间比较长");
	}

	/**
	 * synchronized可以解决线程安全问题，但是对性能有影响
	 * @return
	 */
	public static synchronized LazyModeSingleton getInstance(){
		if(null == lazyModeSingleton){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lazyModeSingleton = new LazyModeSingleton();
		}
		return lazyModeSingleton;
	}

	@Override
	public String toString() {
		return "" + this.hashCode();
	}
}
