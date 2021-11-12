/**
 * 
 */
package org.jpractice.thread.bounce;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author: 作者： xuefei
 * @date: 创建时间：2018-07-03 07:24:43
 * @Description: TODO
 * @version V1.0
 */
public class BallComponent extends JPanel {

	private static final int DEFAULT_WIDTH = 450;

	private static final int DEFAULT_HEIGHT = 350;

	private List<Ball> balls = new ArrayList<>();

	/**
	 * Add a ball to the component
	 * 
	 * @param b
	 *            the ball to add
	 */
	public void add(Ball b) {
		balls.add(b);
	}

	public void paintComponent(Graphics graphics) {

		super.paintComponent(graphics);
		Graphics2D graphics2d = (Graphics2D) graphics;
		for (Ball ball : balls) {
            graphics2d.fill(ball.getShape());
		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
