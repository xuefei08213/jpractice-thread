/**
 * 
 */
package org.jpractice.thread.sync;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2019-06-23 16:59:59
 * @Description: TODO
 * @version V1.0
 */
public class Synchronized {

    public static void main(String[] args) {

        synchronized (Synchronized.class) {
            m();
        }
    }

    public synchronized static void m() {

    }

}
