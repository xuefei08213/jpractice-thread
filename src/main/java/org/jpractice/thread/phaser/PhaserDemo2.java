package org.jpractice.thread.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;

public class PhaserDemo2 {

    public static void main(String[] args) throws InterruptedException {
        CustomPhaser customPhaser = new CustomPhaser(5,3);
        new Runner(customPhaser).start();
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        new Runner(customPhaser).start();
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        new Runner(customPhaser).start();
    }


    static class CustomPhaser extends Phaser{

        private int totalPhaseNum = 3;

        public CustomPhaser(int totalPhaseNum,int parties){
            super(parties);
            this.totalPhaseNum = totalPhaseNum;
        }

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            System.out.println("phase "+phase +" is over, registeredParties is "+ registeredParties);
            return (totalPhaseNum-1) == phase || registeredParties == 0;
        }
    }

    static class Runner extends Thread{

        private Phaser phaser;

        public Runner(Phaser phaser){
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.getName()+" is ready1");
                phaser.arriveAndAwaitAdvance();
                System.out.println(this.getName()+" running...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000));

                System.out.println(this.getName()+" is ready2");
                phaser.arriveAndAwaitAdvance();
                System.out.println(this.getName()+" running...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000));

                System.out.println(this.getName()+" is ready3");
                phaser.arriveAndAwaitAdvance();
                System.out.println(this.getName()+" running...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000));

                System.out.println(this.getName()+" over ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
