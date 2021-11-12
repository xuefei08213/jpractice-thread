/**
 * 
 */
package org.jpractice.thread.atomicity;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-15 11:13:54
 * @Description: TODO
 * @version V1.0
 */
public class CountIncreaseTest {

    public static void main(String[] args) {

        CountIncrease countIncrease = new CountIncrease();

        Thread thread = new Thread(() -> {
            System.out.println("first=" + countIncrease.getCount());
        }, "first");

        Thread thread2 = new Thread(() -> {
            System.out.println("second=" + countIncrease.getCount());
        }, "second");

        thread.start();
        thread2.start();

    }
}
