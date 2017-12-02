/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.io
 * @file UseBuffer.java
 * @author ZuoGuoqing
 * @date 2017年11月26日 下午8:19:24
 * @version 1.0
 */
package name.zuoguoqing.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2017年11月26日 下午8:19:24
 */
public class UseBuffer {
    
    /**
     * 对称编码
     * 
     * @param buffer
     */
    public static void symmetricScrable(CharBuffer buffer) {
        char c1,c2;
        int index;
        while (buffer.hasRemaining()) {
            index = buffer.position();
            c1 = buffer.get();
            if (buffer.hasRemaining()) {
                c2 = buffer.get();
                buffer.put(index, c2);
                buffer.put(index + 1, c1);
            }
            
        }
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        char[] data = "Using Buffer".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);
        cb.rewind();
        System.out.println(cb);
        
        symmetricScrable(cb);
        cb.rewind();
        System.out.println(cb);
        
        symmetricScrable(cb);
        cb.rewind();
        System.out.println(cb);
    }

}
