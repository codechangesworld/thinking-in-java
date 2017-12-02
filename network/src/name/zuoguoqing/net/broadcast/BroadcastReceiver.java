/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net.broadcast
 * @file BroadcastReceiver.java
 * @author zuoguoqing
 * @date 2017年4月3日
 * @version 
 */
package name.zuoguoqing.net.broadcast;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author zuoguoqing
 *
 */
public class BroadcastReceiver {
	private String serverIPAddress = "127.0.0.1";
	private int serverPort = 1234;

	public void work() {
		Socket client = null;
		DataInputStream inputStream = null;

		try {
			client = new Socket(serverIPAddress, serverPort);
			System.out.println("connected with server...");
			inputStream = new DataInputStream(client.getInputStream());
			System.out.println("waiting for broadcast: ");
			int c;
			while (true) {
				c = inputStream.readInt();
				System.out.print((char)c);
			}

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
		BroadcastReceiver receiver = new BroadcastReceiver();
		receiver.work();
	}

}
