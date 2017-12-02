/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net
 * @file FileServer.java
 * @author zuoguoqing
 * @date 2017年4月3日
 * @version 
 */
package name.zuoguoqing.net;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zuoguoqing
 *
 */
public class FileServer {
	private int port = 1234;
	private String filepath = "src//resource//testserver.doc";
	
	public FileServer() {
		
	}
	
	public FileServer(int port, String filepath) {
		super();
		this.port = port;
		this.filepath = filepath;
	}

	public void work() {
		ServerSocket server = null;
		Socket client = null;
		DataInputStream inputStream = null;
		FileOutputStream fos = null;
		
		try {
			server = new ServerSocket(port);
			System.out.println("creating a new server socket...");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			while(true) {
				System.out.println("server is waiting...");
				client = server.accept();
				System.out.println("client: " + client.getInetAddress().getHostAddress());
				System.out.println("reading file from cient...");
				inputStream = new DataInputStream(client.getInputStream());
				fos = new FileOutputStream(filepath);
				int c;
				try {
					while((c = inputStream.readInt()) != -1) {
						fos.write(c);
					}
				} catch (Exception e) {
//					System.out.println(e.getMessage());
				}

				System.out.println("finished reading...");
				fos.close();
				client.close();
				System.out.println("client socket closed!");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileServer server = new FileServer();
		server.work();
	}

}
