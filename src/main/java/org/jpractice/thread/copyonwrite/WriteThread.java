/**
 * 
 */
package org.jpractice.thread.copyonwrite;

import java.util.List;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-09-14 16:28:43
 * @Description: TODO
 * @version V1.0
 */
public class WriteThread implements Runnable {

    private List<Integer> list;

    public WriteThread(List<Integer> list) {
        this.list = list;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        this.list.add(9);
    }

}
