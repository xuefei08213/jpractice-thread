/**
 * 
 */
package org.jpractice.thread.piped;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-12-26 07:15:41
 * @Description: TODO
 * @version V1.0
 */
public class Piped {

    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();

        // 将输出流和输入流进行连接，否则在使用时会抛出IOException
        out.connect(in);
        Thread pinrtThread = new Thread(new Print(in), "PrintThread");
        pinrtThread.start();
        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1) {
                out.write(receive);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            out.close();
        }
    }

    static class Print implements Runnable {

        private PipedReader in;

        public Print(PipedReader in) {
            super();
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.println((char) receive);
                }
            } catch (Exception e) {
            }
        }

    }

}
