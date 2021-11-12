/**
 * 
 */
package org.jpractice.thread.atomicity;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-15 23:04:18
 * @Description: TODO
 * @version V1.0
 */
public class SafetyCounter {

    private AtomicInteger integer = new AtomicInteger(0);;

    public int get() {

        for (int i = 0; i < 100000; i++) {
            integer.addAndGet(1);
        }
        return integer.get();
    }

    public static void main(String[] args) {

        SafetyCounter counter = new SafetyCounter();

        Thread first = new Thread(() -> {
            System.out.println(counter.get());
        }, "first");

        Thread second = new Thread(() -> {
            System.out.println(counter.get());
        }, "second");

        first.start();
        second.start();

    }

}
