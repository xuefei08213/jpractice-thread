/**
 * 
 */
package org.jpractice.thread.copyonwrite;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-09-14 16:29:42
 * @Description: TODO
 * @version V1.0
 */
public class TestCopyOnWriteArrayList {

    private void test() {
        // 1、初始化CopyOnWriteArrayList
        List<Integer> tempList = Arrays.asList(new Integer[] { 1, 2 });
        CopyOnWriteArrayList<Integer> copyList = new CopyOnWriteArrayList<>(tempList);

        // 2、模拟多线程对list进行读和写
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));

        System.out.println("copyList size:" + copyList.size());
    }

    public static void main(String[] args) {
        new TestCopyOnWriteArrayList().test();
    }

}
