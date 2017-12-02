/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net
 * @file ServerSocket1.java
 * @author zuoguoqing
 * @date 2017年4月3日
 * @version 
 */
package name.zuoguoqing.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zuoguoqing
 *
 */
public class SocketServer {
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		try {
			server = new ServerSocket(Integer.parseInt("1234"));
			System.out.println("Creating a new ServerSocket object..");
			System.out.println("server.getLocalPort(): " + server.getLocalPort());
			System.out.println("server object is waiting...");
		} catch (NumberFormatException | IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			while ((client = server.accept()) != null) {
				System.out.println("connected with a client request...");
				System.out.println("client.getPort(): " + client.getPort());
				System.out.println("client.getLocalPort(): " + client.getLocalPort());
				InetAddress address = client.getInetAddress();
				System.out.println("address.getHostName(): " + address.getHostName());
				System.out.println("address.getHostAddress(): " + address.getHostAddress());
				client.close();
				System.out.println("client socket closed!");
			}
		} catch (IOException e) {
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
