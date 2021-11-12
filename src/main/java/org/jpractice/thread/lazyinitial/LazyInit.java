/**
 * 
 */
package org.jpractice.thread.lazyinitial;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-14 07:45:44
 * @Description: TODO
 * @version V1.0
 */
public class LazyInit {

    private static LazyInit instance = null;

    private LazyInit() {
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static LazyInit getInstance() {
        System.out.println(Thread.currentThread().getName());
        if (null == instance) {
            System.out.println("创建实例");
            instance = new LazyInit();
        }
        return instance;
    }

    public void exec() {
        System.out.println("111");
    }

}
