/**
 * 
 */
package org.jpractice.thread.atomicity;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-16 08:04:08
 * @Description: TODO
 * @version V1.0
 */
public class SafetyMultiStateWithSync {

    private int first = 0;
    private int second = 0;

    public synchronized int getEqualCount() {

        int result = 0;
        for (int i = 0; i < 10000; i++) {
            first++;
            second++;
            if (first == second) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SafetyMultiStateWithSync multiStateWithSync = new SafetyMultiStateWithSync();
        Thread first = new Thread(() -> {
            System.out.println(multiStateWithSync.getEqualCount());
        }, "first");

        Thread second = new Thread(() -> {
            System.out.println(multiStateWithSync.getEqualCount());
        }, "second");

        first.start();
        second.start();
    }

}
