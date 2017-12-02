/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoquoqing.gui.swt
 * @file ActiveFrame2.java
 * @author zuoguoqing
 * @date 2017年4月4日
 * @version 
 */
package name.zuoquoqing.gui.swt;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author zuoguoqing
 *
 */
public class ActiveFrame2 {
	Frame frame = new Frame();
	
	public ActiveFrame2() {
		frame.setLocation(300, 300);
		frame.setSize(400, 400);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ActiveFrame2();
		
	}

}
