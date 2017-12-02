/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net
 * @file FileUploadClient.java
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
public class FileUploadClient {
	private String serverIPAddress = "127.0.0.1";
	private int serverPort = 1234;
	private String uploadFile = "src//resource//uploadfile.txt";
	private String uploadFilePath = "src//resource//upload//uploadfile.txt";
	
	public void work() {
		Socket cmdclient = null;
		Socket dataclient = null;
		DataOutputStream cmdOutputStream = null;
		DataOutputStream dataOutputStream = null;
		FileInputStream fis = null;
		
		try {
			cmdclient = new Socket(serverIPAddress, serverPort);
			cmdOutputStream = new DataOutputStream(cmdclient.getOutputStream());
			cmdOutputStream.writeUTF(uploadFilePath);
			
			dataclient = new Socket(serverIPAddress, serverPort);
			dataOutputStream = new DataOutputStream(dataclient.getOutputStream());
			fis = new FileInputStream(uploadFile);
			System.out.println("connected with server,uploading file...");
			int c;
			while((c = fis.read()) != -1) {
				dataOutputStream.writeInt(c);
			}
			System.out.println("file uploaded");
			cmdclient.close();
			dataclient.close();
			System.out.println("client socket closed!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileUploadClient client = new FileUploadClient();
		client.work();
	}

}
