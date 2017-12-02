/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.gui.swing
 * @file Frame1.java
 * @author zuoguoqing
 * @date 2017年4月4日
 * @version 
 */
package name.zuoguoqing.gui.swing;

import javax.swing.JFrame;

/**
 * @author zuoguoqing
 *
 */
public class Frame1 {
	JFrame frame;
	
	public Frame1() {
		frame = new JFrame("Swing Frame");
		frame.setLocation(300, 300);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Frame1();
	}

}
