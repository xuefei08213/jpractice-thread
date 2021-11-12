package org.jpractice.thread.cyclicbarrier;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BankWaterService implements Runnable{

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4,this);

    private Executor executor = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String,Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    private void count(){
        for(int i =0 ;i<4;i++){
            executor.execute(()->{
                sheetBankWaterCount.put(Thread.currentThread().getName(),1);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void run() {
        AtomicInteger result = new AtomicInteger();
        sheetBankWaterCount.forEach((sheetName,count)->{
            result.addAndGet(count);
        });
        sheetBankWaterCount.put("result",result.get());
        System.out.println(result.get());
    }

    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }
}
