/**
 * 
 */
package org.jpractice.thread.connectionpool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-12-27 23:23:15
 * @Description: TODO
 * @version V1.0
 */
public class ConnectionPool {

    private LinkedList<Connection> connections = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                connections.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (connections) {
                // 连接释放后需要进行通知，这样其他消费者能够感知连接池中已经归还一个连接
                connections.addLast(connection);
                connections.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (connections) {
            if (mills < 0) {
                while (connections.isEmpty()) {
                    connections.wait();
                }
                return connections.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (connections.isEmpty() && remaining > 0) {
                    connections.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection connection = null;
                if (!connections.isEmpty()) {
                    connection = connections.removeFirst();
                }
                return connection;
            }
        }
    }

}
