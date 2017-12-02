/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net
 * @file FileClient.java
 * @author zuoguoqing
 * @date 2017年4月3日
 * @version 
 */
package name.zuoguoqing.net;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author zuoguoqing
 *
 */
public class FileClient {
	private String serverIPAddress = "127.0.0.1";
	private int serverPort = 1234;
	private String filepath = "src//resource//testclient.txt";
	
	public FileClient() {
		
	}
	public FileClient(String serverIPAddress, int serverPort, String filepath) {
		super();
		this.serverIPAddress = serverIPAddress;
		this.serverPort = serverPort;
		this.filepath = filepath;
	}

	public void work() {
		Socket client = null;
		DataOutputStream outputStream = null;
		FileInputStream fis = null;
		
		try {
			client = new Socket(serverIPAddress, serverPort);
			System.out.println("creating new client socket...");
			System.out.println("sending local file to server...");
			outputStream = new DataOutputStream(client.getOutputStream());
			fis = new FileInputStream(filepath);
			int c;
			while((c = fis.read()) != -1) {
				outputStream.writeInt(c);
			}
			System.out.println("finished sending...");
			fis.close();
			client.close();
			System.out.println("client socket closed!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileClient client = new FileClient();
		client.work();
	}

}
