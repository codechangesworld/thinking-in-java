/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net
 * @file IntercrossClient.java
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
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author zuoguoqing
 *
 */
public class IntercrossClient {
	private String serverIPAddress = "127.0.0.1";
	private int serverPort = 1234;

	public void work() {
		Socket client = null;
		DataOutputStream outputStream = null;
		DataInputStream inputStream = null;
		BufferedReader reader = null;

		try {
			client = new Socket(serverIPAddress, serverPort);
			System.out.println("connected with server...");
			
			outputStream = new DataOutputStream(client.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("input from keyboard: ");
			int flag = 1;
			int c;
			while (flag == 1) {
				c = reader.read();
				outputStream.writeInt(c);
				if (c == 64) {
					flag = 0;
				}
			}
			
			System.out.println("message sending...\nwaiting to recieve...");
			inputStream = new DataInputStream(client.getInputStream());
			while (flag == 0 ) {
				c = inputStream.readInt();
				if (c == 64) {
					flag = 1;
				} else {
					System.out.print((char)c);
				}
			}
			
			client.close();
			System.out.println("\nclient socket closed!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntercrossClient client = new IntercrossClient();
		client.work();
	}

}
