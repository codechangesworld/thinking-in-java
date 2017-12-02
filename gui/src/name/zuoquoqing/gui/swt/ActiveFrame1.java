/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoquoqing.gui.swt
 * @file ActiveFrame1.java
 * @author zuoguoqing
 * @date 2017年4月4日
 * @version 
 */
package name.zuoquoqing.gui.swt;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author zuoguoqing
 *
 */
public class ActiveFrame1 implements ActionListener {
	Frame frame = new Frame("Active Frame");
	TextField south = new TextField("South");
	TextField center = new TextField("Center");
	
	public ActiveFrame1() {
		frame.setLocation(300, 300);
		frame.setSize(400, 400);
		frame.setVisible(true);
		
		frame.add(south, BorderLayout.SOUTH);
		frame.add(center, BorderLayout.CENTER);
		
		south.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == south) {
			String text = south.getText();
			center.setText(text);
			south.setText("");
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ActiveFrame1();
	}

}
