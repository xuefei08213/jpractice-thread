/**
 * 
 */
package org.jpractice.thread.atomicity;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-16 07:11:15
 * @Description: TODO
 * @version V1.0
 */
public class UnsafetyMultiSates {

    private AtomicInteger first = new AtomicInteger(0);

    private AtomicInteger second = new AtomicInteger(0);

    public int getCount() {

        int result = 0;

        for (int i = 0; i < 10000; i++) {
            first.addAndGet(1);
            second.addAndGet(1);

            if (first.get() == second.get()) {
                result++;
            }
        }

        return result;

    }

    public static void main(String[] args) {

        UnsafetyMultiSates unsafetyMultiSates = new UnsafetyMultiSates();

        Thread thread = new Thread(() -> {
            System.out.println("first=" + unsafetyMultiSates.getCount());
        }, "first");

        Thread thread2 = new Thread(() -> {
            System.out.println("second=" + unsafetyMultiSates.getCount());
        }, "second");

        thread.start();
        thread2.start();

    }

}
