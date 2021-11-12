/**
 * 
 */
package org.jpractice.thread.visibility;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-16 19:34:05
 * @Description: TODO
 * @version V1.0
 */
public class NoVisiblity {

    private static boolean ready;

    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                System.out.println("No ready");
            }
            System.out.println(number);
        }
    }

    private static class WriterThread extends Thread {
        @Override
        public void run() {
            number = 42;
            ready = true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReaderThread readerThread = new ReaderThread();
        readerThread.start();

        WriterThread writerThread = new WriterThread();
        writerThread.start();

    }

}
