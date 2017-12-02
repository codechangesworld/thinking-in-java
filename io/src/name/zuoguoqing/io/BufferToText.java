/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.io
 * @file BufferToText.java
 * @author ZuoGuoqing
 * @date 2017年11月26日 下午3:59:05
 * @version 1.0
 */
package name.zuoguoqing.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2017年11月26日 下午3:59:05
 */
public class BufferToText {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        testDataConvert();
    }

    @SuppressWarnings("resource")
    public static void testDataConvert() throws Exception {
        final int size = 24;
        FileChannel fc = null;
        FileOutputStream fos = null;
        FileInputStream fis = null;
        ByteBuffer buffer = null;
        // output data
        fos = new FileOutputStream("data1.txt");
        fc = fos.getChannel();
        fc.write(ByteBuffer.wrap("Some Char".getBytes())); // encode string into chars by platform default charset
        fc.close();
        // input data
        fis = new FileInputStream("data1.txt");
        fc = fis.getChannel();
        buffer = ByteBuffer.allocate(size);
        fc.read(buffer);
        fc.close();
        // print data
        buffer.flip();
        System.out.println(buffer.asCharBuffer()); // do not decode, disorder code
        buffer.rewind();
        String charset = System.getProperty("file.encoding");
        System.out.println(
                "default charset\t\t" + charset + "\t" + Charset.forName(charset).decode(buffer)); // ok, decode bytes

        fos = new FileOutputStream("data2.txt");
        fc = fos.getChannel();
        fc.write(ByteBuffer.wrap("Some Char".getBytes("UTF-8"))); // encode string
        fc.close();
        fis = new FileInputStream("data2.txt");
        fc = fis.getChannel();
        buffer.clear();
        fc.read(buffer);
        fc.close();
        buffer.flip();
        System.out.println(buffer.position() + "," + buffer.limit() + "," + buffer.capacity());
        String cbs = new String(buffer.array(), "UTF-8"); // decode string
        System.out.println("custom charset\t\tUTF-8\t" + cbs + "end.");
        System.out.println("result length: " + cbs.length());
        for (int i = 0; i < cbs.length(); i++) {
            char c = cbs.charAt(i);
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                System.out.print(c);
            } else {
                System.out.print('#');
            }
        }

        fos = new FileOutputStream("data3.txt");
        fc = fos.getChannel();
        buffer.clear();
        buffer.asCharBuffer().put("Some text");
        fc.write(buffer);
        fc.close();
        fis = new FileInputStream("data3.txt");
        fc = fis.getChannel();
        buffer.clear();
        fc.read(buffer);
        fc.close();
        buffer.flip();
        cbs = buffer.asCharBuffer().toString();
        System.out.println("\nauto encode/decode io\t\t" + cbs + "end.");
        System.out.println("result length: " + cbs.length());
        for (int i = 0; i < cbs.length(); i++) {
            char c = cbs.charAt(i);
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                System.out.print(c);
            } else {
                System.out.print('#');
            }
        }
    }

}
