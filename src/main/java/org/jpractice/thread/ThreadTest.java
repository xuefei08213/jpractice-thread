/**
 * 
 */
package org.jpractice.thread;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-27 14:33:33
 * @Description: TODO
 * @version V1.0
 */
public class ThreadTest {

    public void test() {
        String s = "ssss";
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println(s);
            }
        });

        t.start();
    }

    public static void main(String[] args) {
        ThreadTest test = new ThreadTest();
        test.test();
    }

}
