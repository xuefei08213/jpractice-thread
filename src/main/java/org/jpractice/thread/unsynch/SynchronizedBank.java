/**
 * 
 */
package org.jpractice.thread.unsynch;

import java.util.Arrays;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-11-19 12:45:20
 * @Description: TODO
 * @version V1.0
 */
public class SynchronizedBank {

    private final double[] accounts;

    public SynchronizedBank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
        while (accounts[from] < amount) {
            wait();
        }
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.println(String.format(" %s from %s to %s", amount, from, to));
        accounts[to] += amount;
        System.out.println(String.format(" Total Balance: %s", getTotalBalance()));
        notifyAll();
    }

    public synchronized double getTotalBalance() {
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }

    public int size() {
        return accounts.length;
    }

}
