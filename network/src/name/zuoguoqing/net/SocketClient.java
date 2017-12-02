/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net
 * @file ClientSocket1.java
 * @author zuoguoqing
 * @date 2017年4月3日
 * @version 
 */
package name.zuoguoqing.net;

import java.io.IOException;
import java.net.Socket;

/**
 * @author zuoguoqing
 *
 */
public class SocketClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket client = null;
		try {
			client = new Socket("localhost", Integer.parseInt("1234"));
			System.out.println("Creating a new Socket object...");
			System.out.println("client.getLocalPort(): " + client.getLocalPort());
			System.out.println("client.getPort(): " + client.getPort());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				try {
					client.close();
					System.out.println("client socket closed!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
