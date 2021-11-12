/**
 * 
 */
package org.jpractice.thread.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-12-26 12:29:00
 * @Description: TODO
 * @version V1.0
 */
public class Profiler {

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            // TODO Auto-generated method stub
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost" + Profiler.end() + "mills");
    }

}
