/**
 * 
 */
package org.jpractice.thread.monitor;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-18 12:41:06
 * @Description: TODO
 * @version V1.0
 */
public class MutablePoint {

    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }

}
