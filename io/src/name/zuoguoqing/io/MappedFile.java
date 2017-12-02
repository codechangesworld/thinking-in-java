/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.io
 * @file MappedFile.java
 * @author zuoguoqing
 * @date 2017年3月24日
 * @version 
 */
package name.zuoguoqing.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * @author zuoguoqing
 *
 */
public class MappedFile {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String filename = "data\\mappedaccess.txt";

        accessFileRandomMap(filename);
    }

    private static void accessFileRandomMap(String filenam) {
        int length = 10;
        try (FileChannel fc = new RandomAccessFile(filenam, "rw").getChannel();) {
            MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, length);
            fc.close();
            
            for (int i = 0; i < length / 2 - 1; i++) {
                mbb.asCharBuffer().put(i, 'b');
            }
            mbb.asCharBuffer().put(length / 2 - 1, '#');
            
            System.out.print(mbb.asCharBuffer());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
