/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net
 * @file FileUploadServer.java
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
public class FileUploadServer {
	private int port = 1234;
	
	
	public void work() {
		ServerSocket server = null;
		Socket cmdclient = null;
		Socket dataclient = null;
		DataInputStream cmdInputStream = null;
		DataInputStream dataInputStream = null;
		FileOutputStream fos = null;
		String uploadFile = null;
		try {
			server = new ServerSocket(port);
			while (true) {
				System.out.println("server starting and waiting...");
				cmdclient = server.accept();
				dataclient = server.accept();
				System.out.println("connected with client...");
				System.out.println("uploading file from client...");
				cmdInputStream = new DataInputStream(cmdclient.getInputStream());
				uploadFile = cmdInputStream.readUTF();
				
				dataInputStream = new DataInputStream(dataclient.getInputStream());
				fos = new FileOutputStream(uploadFile);
				int v;
				try {
					while((v = dataInputStream.readInt()) != -1) {
						fos.write(v);
					}
				} catch (Exception e) {
					
				}
				System.out.println("file uploaded...");
				cmdclient.close();
				dataclient.close();
				System.out.println("client closed...");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileUploadServer server = new FileUploadServer();
		server.work();
	}

}
