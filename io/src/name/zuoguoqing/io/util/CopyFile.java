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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
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
            System.out.println("输入文件名：<源文件名> <目的文件名>");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                String[] strings = br.readLine().split(" ");
                source = strings[0];
                destination = strings[1];
            } catch (IOException e) {

            }

        }

        streamCopy(source, destination);

        // channelCopy(source, destination);

        // channelTransfer(source, destination);

        System.out.println("已完成！");
    }

    /**
     * 原始字节流复制
     * 
     * @param source
     * @param destination
     * @return
     */
    public static boolean streamCopy(String source, String destination) {
        if (source == null || destination == null) {
            System.out.println("文件路径不能为空！");
            return false;
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
                BufferedOutputStream bos = new BufferedOutputStream(
                        new FileOutputStream(destination))) {

            int c;
            while ((c = bis.read()) != -1) {
                bos.write(c);
            }
            bos.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 通过文件通道的缓冲区读写实现文件复制
     * 
     * @param source
     * @param destination
     * @return
     */
    public static boolean channelCopy(String source, String destination) {
        if (source == null || destination == null) {
            System.out.println("文件路径不能为空！");
            return false;
        }

        try (FileInputStream fis = new FileInputStream(source);
                FileOutputStream fos = new FileOutputStream(destination);
                FileChannel in = fis.getChannel();
                FileChannel out = fos.getChannel();) {
            
            ByteBuffer bb = ByteBuffer.allocate(1024);
            while (in.read(bb) != -1) {
                bb.flip();
                out.write(bb);
                bb.clear();
            }

        } catch (FileNotFoundException e) {
            System.out.println("文件未找到！");
        } catch (IOException e) {
            System.out.println("文件读写错误！");
        }

        return true;
    }

    /**
     * 通过文件通道的transferTo或者transferFrom方法实现文件复制
     * 
     * @param source
     * @param destination
     * @return
     */
    public static boolean channelTransfer(String source, String destination) {
        if (source == null || destination == null) {
            System.out.println("source file path or destination file path can not be empty");
            return false;
        }

        try (FileInputStream fis = new FileInputStream(source);
                FileOutputStream fos = new FileOutputStream(destination);
                FileChannel in = fis.getChannel();
                FileChannel out = fos.getChannel();) {
            
            in.transferTo(0, in.size(), out);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return false;
        } catch (IOException e) {
            System.out.println("Something wrong about read or write file!");
            return true;
        }

        return true;
    }

}
