/**
 * 
 */
package org.jpractice.thread.unsynch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-11-18 10:18:24
 * @Description: TODO
 * @version V1.0
 */
public class Bank {

    private Condition sufficientFunds;

    private Lock bankLock;

    private final double[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            while (accounts[from] < amount) {
                sufficientFunds.await();
            }
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.println(String.format(" %s from %s to %s", amount, from, to));
            accounts[to] += amount;
            System.out.println(String.format(" Total Balance: %s", getTotalBalance()));
            sufficientFunds.signalAll();
        } finally {
            bankLock.unlock();
        }

    }

    public double getTotalBalance() {
        bankLock.lock();
        try {
            double sum = 0;
            for (double a : accounts) {
                sum += a;
            }
            return sum;
        } finally {
            bankLock.unlock();
        }

    }

    public int size() {
        return accounts.length;
    }

}
