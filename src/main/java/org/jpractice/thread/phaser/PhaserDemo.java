package org.jpractice.thread.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;

public class PhaserDemo {


    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(5);
        new Runner(phaser).start();
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        new Runner(phaser).start();
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        new Runner(phaser).start();
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        new Runner(phaser).start();
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        new Runner(phaser).start();
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000));

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
