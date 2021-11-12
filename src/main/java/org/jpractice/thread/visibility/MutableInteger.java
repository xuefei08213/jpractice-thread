/**
 * 
 */
package org.jpractice.thread.visibility;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-16 21:04:33
 * @Description: TODO
 * @version V1.0
 */
public class MutableInteger {

    private int value;

    public int getValue() {
        System.out.println(Thread.currentThread().getName());
        return value;
    }

    public void setValue(int value) {
        System.out.println(Thread.currentThread().getName());
        this.value = value;
    }

    public static void main(String[] args) {
        MutableInteger integer = new MutableInteger();
        Thread read = new Thread(() -> {
            while (true) {
                System.out.println(integer.getValue());
            }
        }, "read");

        Thread write = new Thread(() -> {
            integer.setValue(11);
        }, "write");

        read.start();
        write.start();



    }

}
