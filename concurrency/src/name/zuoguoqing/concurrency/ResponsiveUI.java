/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency
 * @file ResponsiveUI.java
 * @author zuoguoqing
 * @date 2017年3月20日
 * @version 
 */
package name.zuoguoqing.concurrency;

import java.io.IOException;
/**
 * @author zuoguoqing
 *
 */
public class ResponsiveUI extends Thread {
	private static double value = 1;

	/**
	 * 
	 */
	public ResponsiveUI() {
		setDaemon(true);
		start();
	}

	@Override
	public void run() {
		while (true) {
			value = value + (Math.PI + Math.E) / value;
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		new ResponsiveUI();
		System.in.read();
		System.out.println(value);
	}

}
