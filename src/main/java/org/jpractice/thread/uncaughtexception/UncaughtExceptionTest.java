/**
 * 
 */
package org.jpractice.thread.uncaughtexception;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-11-28 12:44:56
 * @Description: TODO
 * @version V1.0
 */
public class UncaughtExceptionTest {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            throw new RuntimeException("UncaughtExceptionTest");
        };
        Thread thread = new Thread(runnable);
        thread.setUncaughtExceptionHandler(new DemoUncaughtExceptionHandleer());
        thread.start();
    }

}
