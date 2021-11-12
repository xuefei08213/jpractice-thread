/**
 * 
 */
package org.jpractice.thread.lazyinitial;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-14 07:47:45
 * @Description: TODO
 * @version V1.0
 */
public class LazyInitTest {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            LazyInit instance = LazyInit.getInstance();
            System.out.println("first" + instance.toString());
            instance.exec();
        }, "first");

        Thread thread2 = new Thread(() -> {
            LazyInit instance = LazyInit.getInstance();
            System.out.println("second" + instance.toString());
            instance.exec();
        }, "second");

        thread.start();
        thread2.start();

    }

}
