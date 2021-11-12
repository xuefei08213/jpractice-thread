/**
 * 
 */
package org.jpractice.thread.immutable;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2020-05-07 14:15:21
 * @Description: TODO
 * @version V1.0
 */
public class FinalDemo {

    private final String id;

    public FinalDemo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
