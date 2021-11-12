/**
 * 
 */
package org.jpractice.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-12-23 09:37:04
 * @Description: TODO
 * @version V1.0
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
