/**
 * Copyright © zuoguoqing
 *
 * @description tool class for copy file
 * @package name.zuoguoqing.io.util
 * @file CopyFile.java
 * @author zuoguoqing
 * @date 2017年3月24日
 * @version 
 */
package name.zuoguoqing.io.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;

/**
 * @author zuoguoqing
 *
 */
public class CopyFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String source = null;
		String destination = null;
		if (args != null && args.length == 2) {
			source = args[0];
			destination = args[1];
		} else {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				String[] strings = br.readLine().split(" ");
				source = strings[0];
				destination = strings[1];
			} catch (IOException e) {
				
			}
			
		}
		
		copy(source, destination);
	}
	
	public static boolean copy(String source, String destination) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel in = null;
		FileChannel out = null;
		if (source == null || destination == null) {
			System.out.println("source file path or destination file path can not be empty");
			return false;
		}
		try {
			fis = new FileInputStream(source);
			fos = new FileOutputStream(destination);
			in = fis.getChannel();
			out = fos.getChannel();
			in.transferTo(0, in.size(), out);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			return false;
		} catch (IOException e) {
			System.out.println("Something wrong about read or write file!");
			return true;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("Fail to close io stream!");
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println("Fail to close io stream!");
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("Fail to close io stream!");
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					System.out.println("Fail to close io stream!");
				}
			}
		}
		
		return true;
	}

}
