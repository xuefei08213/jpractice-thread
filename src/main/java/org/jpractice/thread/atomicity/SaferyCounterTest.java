/**
 * 
 */
package org.jpractice.thread.atomicity;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-15 23:06:27
 * @Description: TODO
 * @version V1.0
 */
public class SaferyCounterTest {

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
