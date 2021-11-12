/**
 * 
 */
package org.jpractice.thread.blockingqueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-11-25 15:19:08
 * @Description: TODO
 * @version V1.0
 */
public class BlockingQueueTest {

    private static final int FILE_QUEUE_SIZE = 10;

    private static final int SEARCH_THREADS = 100;

    private static final File DUMMY = new File("");

    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {

        try (Scanner in = new Scanner(System.in)) {
            System.out.println("enter base directory:");
            String directory = in.nextLine();
            System.out.println("enter keyword:");
            String keyword = in.nextLine();

            Runnable enumerator = () -> {
                try {
                    enumerate(new File(directory));
                    queue.put(DUMMY);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            };
            new Thread(enumerator).start();

            for (int i = 1; i < SEARCH_THREADS; i++) {
                Runnable searcher = () -> {
                    try {
                        boolean done = false;
                        while (!done) {
                            File file = queue.take();
                            if (file == DUMMY) {
                                queue.put(file);
                                done = true;
                            } else {
                                search(file, keyword);
                            }
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                };
                new Thread(searcher).start();
            }

        } catch (Exception e) {
            // TODO: handle exception
        }


    }

    public static void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                enumerate(file);
            } else {
                queue.put(file);
            }
        }
    }

    public static void search(File file, String keyword) throws FileNotFoundException {
        try (Scanner in = new Scanner(file, "utf-8")) {
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword)) {
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
                }
            }
        }
    }

}
