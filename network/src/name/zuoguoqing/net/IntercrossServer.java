/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net
 * @file IntercrossServer.java
 * @author zuoguoqing
 * @date 2017年4月3日
 * @version 
 */
package name.zuoguoqing.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zuoguoqing
 *
 */
public class IntercrossServer {
	private int port = 1234;

	public void work() {
		ServerSocket server = null;
		Socket client = null;
		DataInputStream inputStream = null;
		DataOutputStream outputStream = null;
		BufferedReader reader = null;

		try {
			server = new ServerSocket(port);
			System.out.println("starting server, waiting...");
			client = server.accept();
			System.out.println("connected with client...\nwaiting...");
			inputStream = new DataInputStream(client.getInputStream());
			int flag = 0;
			int c;
			while (flag == 0) {
				c = inputStream.readInt();
				if (c == 64) {
					flag = 1;
				} else {
					System.out.print((char) c);
				}
			}
			
			System.out.println("\nreceived message...\nwaiting for sending from keyboard...");
			outputStream = new DataOutputStream(client.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(System.in));
			while (flag == 1) {
				c = reader.read();
				outputStream.writeInt(c);
				if (c == 64) {
					flag = 0;
				}
			}
			
			System.out.println("finished sending...");
			client.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntercrossServer server = new IntercrossServer();
		server.work();
	}

}
