/**
 * 
 */
package org.jpractice.thread.interrupt;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-11-28 07:17:12
 * @Description: TODO
 * @version V1.0
 */
public class InterruptTest {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            Thread.currentThread().interrupt();
            // islnterrupted 方法是一个实例方法， 可用来检验是否有线程被中断
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("线程被中断");
            }

            // Interrupted方法是一个静态方法，它检测当前的线程是否被中断。而且，调用interrupted方法会清除该线程的中断状态。
            if (Thread.interrupted()) {
                System.out.println("线程已经中断，调用Interrupted方法，中断状态被清除");
            }
            try {
                // 在中断位置调用sleep方法，它不会休眠。相反，它将清除这一状态并抛出InterruptedException异常
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(" 事实上");
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
