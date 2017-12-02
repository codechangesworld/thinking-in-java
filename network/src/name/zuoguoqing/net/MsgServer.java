/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net
 * @file ServerSocket2.java
 * @author zuoguoqing
 * @date 2017年4月3日
 * @version 
 */
package name.zuoguoqing.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zuoguoqing
 *
 */
public class MsgServer {
//	private String serverIPAddress = "127.0.0.1";
	private String serverMsg = "message comes from local server";
	private int serverPort = 1234;
	
	public MsgServer() {

	}
	public MsgServer(int port, String message) {
		serverPort = port;
		message = serverMsg;
	}
	
	public void work() {
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;
		try {
			serverSocket = new ServerSocket(serverPort);
			System.out.println("creating a new server object at [" + serverPort + "] port...");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			while(true) {
				System.out.println("server starting warting...");
				clientSocket = serverSocket.accept();
				System.out.println("client hostname: " + clientSocket.getInetAddress().getHostName()
						+ "\tclient address: " + clientSocket.getInetAddress().getHostAddress()
						+ "\tclient port: " + clientSocket.getPort());
				
				System.out.println("reading message from client...");
				dataInputStream = new DataInputStream(clientSocket.getInputStream());
				System.out.println("client message: " + dataInputStream.readUTF());
				
				System.out.println("writing message to client...");
				dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
				dataOutputStream.writeUTF(serverMsg);
				
				clientSocket.close();
				System.out.println("all work finished!\nclient closed!");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MsgServer server = new MsgServer();
		server.work();
	}

}
