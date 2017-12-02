/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.net.broadcast
 * @file BroadcastServer.java
 * @author zuoguoqing
 * @date 2017年4月3日
 * @version 
 */
package name.zuoguoqing.net.broadcast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zuoguoqing
 *
 */
public class BroadcastServer {
	private int port = 1234;
	ConcurrentHashMap<Socket, DataOutputStream> socketMap = new ConcurrentHashMap<>();
	
	/**
	 * 
	 */
	public void work() {
		ServerSocket server = null;
		Socket socket = null;
		DataOutputStream outputStream = null;
		try {
			server = new ServerSocket(port);
			System.out.println("starting server...");
			while (true) {
				System.out.println("server is waiting for connected...");
				try {
					socket = server.accept();
					System.out.println("client IP:Port : " + socket.getInetAddress().getHostAddress() 
							+ ":" + socket.getLocalPort());
					outputStream = new DataOutputStream(socket.getOutputStream());
					socketMap.put(socket, outputStream);
					new Thread(new Connector(socket, socketMap)).start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end while
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}//end finally
	}
	
	/**
	 * 
	 * @author zuoguoqing
	 *
	 */
	class Connector implements Runnable {
		private Socket socket;
		private ConcurrentHashMap<Socket, DataOutputStream> map;
		
		public Connector(Socket socket, ConcurrentHashMap<Socket, DataOutputStream> map) {
			this.socket = socket;
			this.map = map;
		}
		
		@Override
		public void run() {
			DataInputStream inputStream = null;
			Iterator<DataOutputStream> iterator = null;
			DataOutputStream outputStream = null;
			try {
				int v;
				inputStream = new DataInputStream(socket.getInputStream());
				while (true) {
					v = inputStream.readInt();
					synchronized (map) {
						iterator = map.values().iterator();
						while (iterator.hasNext()) {
							outputStream = iterator.next();
							try {
								outputStream.writeInt(v);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}//end while
					}//end synchronized
				}//end while
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				synchronized (map) {
					map.remove(socket);
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}//end finally
			
			
		}//end run()
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BroadcastServer server = new BroadcastServer();
		server.work();
	}

}
