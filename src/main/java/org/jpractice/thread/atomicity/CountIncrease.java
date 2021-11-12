/**
 * 
 */
package org.jpractice.thread.atomicity;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-15 11:12:21
 * @Description: TODO
 * @version V1.0
 */
public class CountIncrease {

    private int count;

    public int getCount() {
        System.out.println(Thread.currentThread().getName());

        for (int i = 0; i < 1000000; i++) {
            count++;
        }

        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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
