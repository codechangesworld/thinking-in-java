/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net.broadcast
 * @file BroadcastSpeaker.java
 * @author zuoguoqing
 * @date 2017年4月3日
 * @version 
 */
package name.zuoguoqing.net.broadcast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author zuoguoqing
 *
 */
public class BroadcastSpeaker {
	private String serverIPAddress = "127.0.0.1";
	private int serverPort = 1234;

	public void work() {
		Socket client = null;
		DataOutputStream outputStream = null;
		BufferedReader reader = null;

		try {
			client = new Socket(serverIPAddress, serverPort);
			System.out.println("connected with server...");
			outputStream = new DataOutputStream(client.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("input from keyboard: ");
			int c;
			while (true) {
				c = reader.read();
				outputStream.writeInt(c);
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
		BroadcastSpeaker speaker = new BroadcastSpeaker();
		speaker.work();
	}

}
