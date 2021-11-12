/**
 * 
 */
package org.jpractice.thread.future;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-11-26 22:27:40
 * @Description: TODO
 * @version V1.0
 */
public class FutureTest {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("enter base directory:");
            String directory = in.nextLine();
            System.out.println("enter keyword:");
            String keyword = in.nextLine();

            MatchCounter matchCounter = new MatchCounter(new File(directory), keyword);
            FutureTask<Integer> task = new FutureTask<>(matchCounter);
            Thread t = new Thread(task);
            t.start();
            try {
                System.out.println(task.get() + " matching files ");
            } catch (Exception e) {
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}

class MatchCounter implements Callable<Integer> {

    private File directory;

    private String keyword;

    public MatchCounter(File directory, String keyword) {
        super();
        this.directory = directory;
        this.keyword = keyword;
    }

    @Override
    public Integer call() throws Exception {

        int count = 0;

        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    MatchCounter matchCounter = new MatchCounter(file, keyword);
                    FutureTask<Integer> futureTask = new FutureTask<>(matchCounter);
                    results.add(futureTask);
                    Thread t = new Thread(futureTask);
                    t.start();
                } else {
                    if (search(file)) {
                        count++;
                    }
                }
            }

            for (Future<Integer> result : results) {
                try {
                    count += result.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return count;
    }

    public boolean search(File file) {
        try {
            try (Scanner in = new Scanner(file, "utf-8")) {

                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyword)) {
                        found = true;
                    }
                }
                return found;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
