/**
 * 
 */
package org.jpractice.thread.join;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-12-26 08:41:58
 * @Description: TODO
 * @version V1.0
 */
public class Join {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        System.out.println("main");
    }

    static class Domino implements Runnable {

        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "terminate.");
        }

    }

}
