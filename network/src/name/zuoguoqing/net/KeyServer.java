/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net
 * @file KeyServer.java
 * @author zuoguoqing
 * @date 2017年4月3日
 * @version 
 */
package name.zuoguoqing.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zuoguoqing
 *
 */
public class KeyServer {
	private int port = 1234;
	
	public void work() {
		ServerSocket server = null;
		Socket client = null;
		DataInputStream inputStream = null;
		
		try {
			server = new ServerSocket(port);
			client = server.accept();
			System.out.println("connected with client...\nwaiting for input...");
			inputStream = new DataInputStream(client.getInputStream());
			int c;
			while ((c = inputStream.readInt()) != -1) {
				System.out.print((char)c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KeyServer server = new KeyServer();
		server.work();
	}

}
