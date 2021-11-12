/**
 * 
 */
package org.jpractice.thread.unsynch;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-11-22 23:09:57
 * @Description: TODO
 * @version V1.0
 */
public class SimpleDateFormatTest {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static final ThreadLocal<SimpleDateFormat> LOCAL = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) {

        for (int i = 0; i < 2000; i++) {
            Runnable r = () -> {
                System.out.println(dateFormat.format(new Date()));
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }

}
