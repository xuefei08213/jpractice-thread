/**
 * 
 */
package org.jpractice.thread.atomicity;

import java.util.Vector;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-16 15:44:20
 * @Description: TODO
 * @version V1.0
 */
public class VectorTest {

    public static void main(String[] args) {

        Vector<Integer> integers = new Vector<>();
        integers.add(0);

        Thread thread = new Thread(() -> {
            if (integers.contains(0)) {
                System.out.println("first包含0");
                System.out.println("first执行删除" + integers.remove(0));
            }

        }, "first");

        Thread thread2 = new Thread(() -> {
            if (integers.contains(0)) {
                System.out.println("second执行删除" + integers.remove(0));
            }

        }, "second");

        thread.start();
        thread2.start();

    }

}
