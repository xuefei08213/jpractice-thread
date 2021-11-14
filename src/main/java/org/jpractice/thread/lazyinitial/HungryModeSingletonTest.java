package org.jpractice.thread.lazyinitial;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @author: xuefei
 * @create 2021/11/14 09:14:56
 */
public class HungryModeSingletonTest {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for(int i = 0;i<10;i++){
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(HungryModeSingleton.getInstance());
				}
			});
		}
		executorService.shutdown();
	}

}

class HungryModeSingleton{
	private static HungryModeSingleton hungryModeSingleton = new HungryModeSingleton();

	private HungryModeSingleton(){
		try {
			Thread.sleep(1000);
			System.out.println("构建这个对象耗时较长");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static HungryModeSingleton getInstance(){
		return hungryModeSingleton;
	}

	@Override
	public String toString() {
		return this.hashCode() + "";
	}
}
