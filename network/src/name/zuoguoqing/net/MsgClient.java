/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net
 * @file ClientSocket2.java
 * @author zuoguoqing
 * @date 2017年4月3日
 * @version 
 */
package name.zuoguoqing.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author zuoguoqing
 *
 */
public class MsgClient {
	private String serverName = "LocalhostName";
	private byte[] serverIPBytes = {127, 0, 0, 1};
	private int serverPort = 1234;
	private String clientMsg = "message comes from client";
	private InetAddress inetAddress = null;
	
	public MsgClient() {
		try {
			inetAddress = InetAddress.getByAddress(serverName, serverIPBytes);
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
	}
	public MsgClient(String host, int port) {
		serverName = host;
		serverPort = port;
		try {
			inetAddress = InetAddress.getByAddress(serverName, serverIPBytes);
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void work() {
		Socket clientSocket = null;
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;
		try {
			clientSocket = new Socket(inetAddress, serverPort);
			System.out.println("creating a new client socket...");
			System.out.println("Server Name: " + clientSocket.getInetAddress().getHostName()
					+ "\tServer Address: " + clientSocket.getInetAddress().getHostAddress()
					+ "\tServer Port: " + clientSocket.getPort());
			dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
			System.out.println("sending message to server socket...");
			dataOutputStream.writeUTF(clientMsg);
			System.out.println("recieving message from server...");
			dataInputStream = new DataInputStream(clientSocket.getInputStream());
			System.out.println("server message: [" + dataInputStream.readUTF() + "]");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			if(clientSocket != null) {
				try {
					clientSocket.close();
					System.out.println("client socket closed!");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MsgClient client = new MsgClient();
		client.work();
	}

}
