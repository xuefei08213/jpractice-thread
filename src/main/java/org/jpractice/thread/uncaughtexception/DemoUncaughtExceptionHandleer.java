/**
 * 
 */
package org.jpractice.thread.uncaughtexception;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-11-28 12:43:57
 * @Description: TODO
 * @version V1.0
 */
public class DemoUncaughtExceptionHandleer implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getName());
        System.out.println(e.getMessage());
    }

}
