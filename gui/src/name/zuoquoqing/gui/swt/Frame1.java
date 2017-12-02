/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoquoqing.gui.swt
 * @file Frame1.java
 * @author zuoguoqing
 * @date 2017年4月4日
 * @version 
 */
package name.zuoquoqing.gui.swt;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.TextField;

/**
 * @author zuoguoqing
 *
 */
public class Frame1 {
	Frame frame;
	@SuppressWarnings("deprecation")
	public Frame1() {
		frame = new Frame();
		frame.setSize(300, 300);
		frame.setLocation(400, 300);
		frame.setCursor(Cursor.HAND_CURSOR);
		frame.setVisible(true);
		
		TextField textField = new TextField("text field");
		frame.add(textField);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Frame1();
	}

}
