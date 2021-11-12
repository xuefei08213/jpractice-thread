/**
 * 
 */
package org.jpractice.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-11-28 06:27:12
 * @Description: TODO
 * @version V1.0
 */
public class ForkJoinTest {

    public static void main(String[] args) {
        final int size = 10000000;
        double[] numbers = new double[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = Math.random();
        }
        Counter counter = new Counter(numbers, 0, numbers.length, x -> x > 0.5);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(counter);
        System.out.println(counter.join());
    }

}

class Counter extends RecursiveTask<Integer> {
    public static final int threshold = 1000;
    private double[] values;

    private int from;
    private int to;
    private DoublePredicate filter;

    public Counter(double[] values, int from, int to, DoublePredicate filter) {
        super();
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    @Override
    protected Integer compute() {
        if(to -from <threshold) {
            int count = 0;
            for(int i = from;i<to;i++) {
                if(filter.test(values[i])) {
                    count++;
                }
            }
            return count;
        } else {
            int mid = (from + to) / 2;
            Counter first = new Counter(values, from, mid, filter);
            Counter second = new Counter(values, mid, to, filter);
            invokeAll(first, second);
            return first.join() + second.join();
        }
    }

}
