package org.jpractice.thread.lazyinitial;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description
 * @author: xuefei
 * @create 2021/11/14 09:30:56
 */
public class DoubleCheckSingletonTest {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for(int i = 0;i<10;i++){
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(DoubleCheckSingleton.getInstance());
				}
			});
		}
		executorService.shutdown();
	}

}


class DoubleCheckSingleton{

	private static DoubleCheckSingleton doubleCheckSingleton = null;

	private DoubleCheckSingleton(){
		System.out.println("创建任务耗时较长");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static DoubleCheckSingleton getInstance(){

		if(null == doubleCheckSingleton){
			synchronized (DoubleCheckSingleton.class){
				// 此处必须进行双重检查
				if(doubleCheckSingleton==null){
					doubleCheckSingleton = new DoubleCheckSingleton();
				}


			}
		}
		return doubleCheckSingleton;
	}

	@Override
	public String toString() {
		return this.hashCode() + "";
	}
}
