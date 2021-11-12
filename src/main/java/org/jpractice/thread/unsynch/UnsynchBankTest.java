/**
 * 
 */
package org.jpractice.thread.unsynch;

import java.util.logging.Logger;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-11-18 10:15:17
 * @Description: TODO
 * @version V1.0
 */
public class UnsynchBankTest {

    private static Logger logger = Logger.getLogger("");

    public static final int NACCOUNTS = 10;

    public static final double INITIAL_BALANCE = 1000;

    public static final double MAX_AMOUNT = 2000;

    public static final int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
