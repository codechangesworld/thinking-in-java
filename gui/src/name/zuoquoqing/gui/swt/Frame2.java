/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoquoqing.gui.swt
 * @file Frame2.java
 * @author zuoguoqing
 * @date 2017年4月4日
 * @version 
 */
package name.zuoquoqing.gui.swt;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextField;

/**
 * @author zuoguoqing
 *
 */
public class Frame2 extends Frame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Frame2() {
		super("frame2");
		
		setLocation(300, 200);
		setSize(400, 400);
		setVisible(true);
		
		TextField tf1 = new TextField("East");
		TextField tf2 = new TextField("South");
		TextField tf3 = new TextField("Western");
		TextField tf4 = new TextField("North");
		TextField tf5 = new TextField("Center");
		
		add(tf1, BorderLayout.EAST);
		add(tf2, BorderLayout.SOUTH);
		add(tf3, BorderLayout.WEST);
		add(tf4, BorderLayout.NORTH);
		add(tf5, BorderLayout.CENTER);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Frame2();
	}

}
