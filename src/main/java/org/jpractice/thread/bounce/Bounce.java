/**
 * 
 */
package org.jpractice.thread.bounce;

import java.awt.EventQueue;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-11-16 12:45:57
 * @Description: TODO
 * @version V1.0
 */
public class Bounce {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            // JFrame frame = new BounceFrame();
            // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // frame.setVisible(true);
        });
    }
}

//class BounceFrame extends JFrame{
//    private BallComponent comp;
//    public static final int STEPS = 1000;
//    public static final int DELAY = 3;
//
//    public BounceFrame() {
//
//        setTitle("Bounce");
//        comp = new BallComponent();
//        add(comp, BorderLayout.CENTER);
//
//        JPanel buttonPanel = new JPanel();
//        addButton(buttonPanel, "Start", event -> addBall());
//        addButton(buttonPanel, "Stop", event -> System.exit(0));
//        add(buttonPanel, BorderLayout.SOUTH);
//        pack();
//    }
//
//    public void addButton(Container container, String title, ActionListener actionListener) {
//        JButton button = new JButton(title);
//        container.add(button);
//        button.addActionListener(actionListener);
//    }
//
//    public void addBall() {
//        try {
//            Ball ball = new Ball();
//            comp.add(ball);
//            for (int i = 0; i < STEPS; i++) {
//                ball.move(comp.getBounds());
//                comp.paint(comp.getGraphics());
//                Thread.sleep(DELAY);
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//    }

// }
