/**
 * 
 */
package org.jpractice.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2019-08-02 22:32:57
 * @Description: TODO
 * @version V1.0
 */
public class ParkTest {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(1);
            LockSupport.park();
            System.out.println(2);
        });
        thread.start();

        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Thread thread2 = new Thread(() -> {
            LockSupport.unpark(thread);
        });
        thread2.start();
    }

}
