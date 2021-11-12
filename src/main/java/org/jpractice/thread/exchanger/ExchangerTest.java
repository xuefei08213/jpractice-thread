package org.jpractice.thread.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerTest {


    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ExchangerRunnable exchangerRunnableA = new ExchangerRunnable(exchanger,"A");
        ExchangerRunnable exchangerRunnableB = new ExchangerRunnable(exchanger,"B");
        ExchangerRunnable exchangerRunnableC = new ExchangerRunnable(exchanger,"C");
        ExchangerRunnable exchangerRunnableD = new ExchangerRunnable(exchanger,"D");

        new Thread(exchangerRunnableA).start();
        new Thread(exchangerRunnableB).start();
        new Thread(exchangerRunnableC).start();
        new Thread(exchangerRunnableD).start();

    }


}


class ExchangerRunnable implements  Runnable{

    Exchanger<String> exchanger = null;
    String object = null;

    public ExchangerRunnable(Exchanger<String> exchanger,String object){
        this.exchanger = exchanger;
        this.object = object;
    }

    @Override
    public void run() {
        try{
            Object previous = this.object;
            if("C".equals(previous)){
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " 对数据A的处理耗时1s");
            }else if("D".equals(previous)){
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName() + " 对数据B的处理耗时2s");
            }

            // 两个对象必须在此处会和，只有一个线程调用
            this.object = this.exchanger.exchange(this.object);
            System.out.println(Thread.currentThread().getName() + " exchanged " +previous + " for " + this.object);
        }catch (InterruptedException e){

        }

    }
}