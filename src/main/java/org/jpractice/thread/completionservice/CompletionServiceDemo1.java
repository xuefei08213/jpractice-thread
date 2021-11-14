package org.jpractice.thread.completionservice;

import java.util.concurrent.*;

public class CompletionServiceDemo1 {
    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            CompletionService cs = new ExecutorCompletionService(executorService);
            cs.submit(new SalaryCallable0(100,1000));
            cs.submit(new SalaryCallable1(200,2000));
            //take方法获取并不会抛出异常
            Future f1 = cs.take();
            System.out.println("f1:" + f1);
            Future f2 = cs.take();
            System.out.println("f2:" + f2);
            //get方法会抛出异常
            System.out.println("f1:" + f1.get());
            System.out.println("f2" + f2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}