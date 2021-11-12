/**
 * 
 */
package org.jpractice.thread.threadpool;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-12-28 08:41:08
 * @Description: TODO
 * @version V1.0
 */
public interface ThreadPool<Job extends Runnable> {

    // 执行一个Job，这个Job需要实现Runnable
    void execute(Job job);

    // 关闭线程池
    void shutdown();

    // 增加工作者线程
    void addWorkers(int num);

    // 减少工作者线程
    void removeWorker(int num);

    // 得到正在等待执行的任务数量
    int getJobSize();

}
