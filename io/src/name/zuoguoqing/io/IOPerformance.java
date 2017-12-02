/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.io
 * @file IOPerformance.java
 * @author ZuoGuoqing
 * @date 2017年11月27日 下午4:16:06
 * @version 1.0
 */
package name.zuoguoqing.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2017年11月27日 下午4:16:06
 */
public class IOPerformance {

    private IOTest[] testers = { new IOStream(), new IOBufferedStream(), new IOReaderWriter(),
            new IOBufferedReaderWriter(), new IOBufferChannel(), new IORandomAccess(),
            new IOMapped() };

    public static int NUM_Count = 0xFFFFF; // 2^24 = 16M

    private static PrintWriter OUT;

    static {
        try {
            OUT = new PrintWriter(
                    new BufferedOutputStream(new FileOutputStream("result.txt", true)), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        IOPerformance.runTest();

    }

    /**
     * 
     */
    public static void runTest() {
        IOPerformance ioTester = new IOPerformance();
        String filename = "iotest.tmp";

        String out = "****************************************";
        System.out.println(out);
        OUT.println(out);
        
        out = "**         IO Performace Test         **";
        System.out.println(out);
        OUT.println(out);
        
        out = "****************************************";
        System.out.println(out);
        OUT.println(out);
        OUT.flush();

        long begin = System.currentTimeMillis();
        ioTester.testWrite(filename);
        ioTester.testRead(filename);
        ioTester.testReatAndWrite(filename);
        long end = System.currentTimeMillis();

        System.out.println(out);
        OUT.println(out);
        
        out = String.format("%-24s %12.2f MB", "Test File Size:", ((double)IOPerformance.NUM_Count) * 2 / 1024 / 1024);
        System.out.println(out);
        OUT.println(out);

        out = String.format("%-24s %12.2f s", "Cost Time: ", ((double)(end - begin)) / 60 / 60);
        System.out.println(out);
        OUT.println(out);
        OUT.flush();
    }

    /**
     * test file read performance
     * 
     * @param filename
     */
    public void testRead(String filename) {
        String out = "-------------- IO Read Test ------------";
        System.out.println(out);
        OUT.println(out);

        long begin, end;
        for (IOTest tester : testers) {
            begin = System.currentTimeMillis();
            tester.read(filename);
            end = System.currentTimeMillis();

            out = String.format("%-24s %12d ms", tester.getClass().getSimpleName(), end - begin);
            System.out.println(out);
            OUT.println(out);
        }
        
        OUT.flush();

    }

    /**
     * test file write performance
     * 
     * @param filename
     */
    public void testWrite(String filename) {
        String out = "------------- IO Write Test ------------";
        System.out.println(out);
        OUT.println(out);

        long begin, end;
        for (IOTest tester : testers) {
            begin = System.currentTimeMillis();
            tester.write(filename);
            end = System.currentTimeMillis();

            out = String.format("%-24s %12d ms", tester.getClass().getSimpleName(), end - begin);
            System.out.println(out);
            OUT.println(out);
        }
        
        OUT.flush();

    }

    /**
     * test file read and write performance
     * 
     * @param filename
     */
    public void testReatAndWrite(String filename) {
        String out = "----------- IO Read/Write Test ----------";
        System.out.println(out);
        OUT.println(out);

        long begin, end;
        for (IOTest tester : testers) {
            begin = System.currentTimeMillis();
            tester.readAndWrite(filename);
            end = System.currentTimeMillis();

            out = String.format("%-24s %12d ms", tester.getClass().getSimpleName(), end - begin);
            System.out.println(out);
            OUT.println(out);
        }
        
        OUT.flush();

    }

    /**
     * common io test interface
     *
     * @author ZuoGuoqing
     * @version 1.0
     * @date 2017年11月27日 下午6:26:37
     */
    interface IOTest {
        /**
         * file read test
         * 
         * @param filename
         */
        void read(String filename);

        /**
         * file write test
         * 
         * @param filename
         */
        void write(String filename);

        /**
         * file read and write test
         * 
         * @param filename
         */
        void readAndWrite(String filename);
    }

    /**
     * original io stream test, no buffer
     *
     * @author ZuoGuoqing
     * @version 1.0
     * @date 2017年11月27日 下午6:27:04
     */
    static class IOStream implements IOTest {
        private final int numCount;

        public IOStream() {
            numCount = IOPerformance.NUM_Count;
        }

        public IOStream(int size) {
            this.numCount = size;
        }

        @Override
        public void read(String filename) {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
                for (int i = 0; i < numCount; i++) {
                    dis.readChar();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void write(String filename) {
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
                for (int i = 0; i < numCount; i++) {
                    dos.writeChar(i);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void readAndWrite(String filename) {

        }

    }

    /**
     * buffered io stream test
     *
     * @author ZuoGuoqing
     * @version 1.0
     * @date 2017年11月27日 下午6:27:36
     */
    static class IOBufferedStream implements IOTest {
        private final int numCount;

        public IOBufferedStream() {
            numCount = IOPerformance.NUM_Count;
        }

        public IOBufferedStream(int count) {
            this.numCount = count;
        }

        @Override
        public void read(String filename) {
            try (DataInputStream dis = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(filename)))) {
                for (int i = 0; i < numCount; i++) {
                    dis.readChar();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void write(String filename) {
            try (DataOutputStream dos = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(filename)))) {
                for (int i = 0; i < numCount; i++) {
                    dos.writeChar(i);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void readAndWrite(String filename) {

        }

    }

    /**
     * reader and writer io test
     *
     * @author ZuoGuoqing
     * @version 1.0
     * @date 2017年11月27日 下午6:36:08
     */
    static class IOReaderWriter implements IOTest {
        private final int numCount;

        public IOReaderWriter() {
            numCount = IOPerformance.NUM_Count;
        }

        public IOReaderWriter(int count) {
            numCount = count;
        }

        @Override
        public void read(String filename) {
            try (FileReader reader = new FileReader(filename)) {
                for (int i = 0; i < numCount; i++) {
                    reader.read();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void write(String filename) {
            try (FileWriter writer = new FileWriter(filename)) {
                for (int i = 0; i < numCount; i++) {
                    writer.write(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void readAndWrite(String filename) {

        }

    }

    /**
     * buffered reader and writer io test
     *
     * @author ZuoGuoqing
     * @version 1.0
     * @date 2017年11月27日 下午6:37:50
     */
    static class IOBufferedReaderWriter implements IOTest {
        private final int numCount;

        public IOBufferedReaderWriter() {
            numCount = IOPerformance.NUM_Count;
        }

        public IOBufferedReaderWriter(int count) {
            numCount = count;
        }

        @Override
        public void read(String filename) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                for (int i = 0; i < numCount; i++) {
                    reader.read();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void write(String filename) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                for (int i = 0; i < numCount; i++) {
                    writer.write(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void readAndWrite(String filename) {

        }

    }

    /**
     * buffer and file channel test
     *
     * @author ZuoGuoqing
     * @version 1.0
     * @date 2017年11月27日 下午6:34:02
     */
    static class IOBufferChannel implements IOTest {
        private final int numCount;

        public IOBufferChannel() {
            numCount = IOPerformance.NUM_Count;
        }

        public IOBufferChannel(int numCount) {
            this.numCount = numCount;
        }

        @Override
        public void read(String filename) {
            try (FileChannel fc = new FileInputStream(filename).getChannel()) {
                ByteBuffer bb = ByteBuffer.allocate(numCount * 2);
                fc.read(bb);
                CharBuffer cb = bb.asCharBuffer();
                cb.flip();
                while (cb.hasRemaining()) {
                    cb.get();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void write(String filename) {
            try (FileChannel fc = new FileOutputStream(filename).getChannel()) {
                ByteBuffer bb = ByteBuffer.allocate(numCount * 2);
                CharBuffer cb = bb.asCharBuffer();
                for (int i = 0; i < numCount; i++) {
                    cb.put((char) i);
                }
                bb.flip();
                fc.write(bb);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void readAndWrite(String filename) {

        }

    }

    /**
     * random access file test
     *
     * @author ZuoGuoqing
     * @version 1.0
     * @date 2017年11月27日 下午8:32:00
     */
    static class IORandomAccess implements IOTest {
        private final int numCount;

        public IORandomAccess() {
            numCount = IOPerformance.NUM_Count;
        }

        public IORandomAccess(int numCount) {
            this.numCount = numCount;
        }

        @Override
        public void read(String filename) {
            try (RandomAccessFile raf = new RandomAccessFile(filename, "r")) {
                for (int i = 0; i < numCount; i++) {
                    raf.readChar();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void write(String filename) {
            try (RandomAccessFile raf = new RandomAccessFile(filename, "rw")) {
                raf.seek(0);
                for (int i = 0; i < numCount; i++) {
                    raf.writeChar(i);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void readAndWrite(String filename) {
            try (RandomAccessFile raf = new RandomAccessFile(filename, "rw")) {
                raf.seek(0);
                raf.writeChar(1);
                for (int i = 1; i < numCount; i++) {
                    raf.seek(raf.length() - 2);
                    raf.writeChar(raf.readChar());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * mapped buffer io channel test
     *
     * @author ZuoGuoqing
     * @version 1.0
     * @date 2017年11月27日 下午6:27:53
     */
    static class IOMapped implements IOTest {
        private final int numCount;

        public IOMapped() {
            numCount = IOPerformance.NUM_Count;
        }

        public IOMapped(int count) {
            this.numCount = count;
        }

        @Override
        public void read(String filename) {
            try (FileChannel fc = new FileInputStream(filename).getChannel()) {
                CharBuffer cb = fc.map(MapMode.READ_ONLY, 0, numCount * 2).asCharBuffer();
                while (cb.hasRemaining()) {
                    cb.get();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void write(String filename) {
            try (FileChannel fc = new RandomAccessFile(filename, "rw").getChannel()) {
                CharBuffer cb = fc.map(MapMode.READ_WRITE, 0, numCount * 2).asCharBuffer();
                for (int i = 0; i < numCount; i++) {
                    cb.put((char) i);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void readAndWrite(String filename) {
            try (FileChannel fc = new RandomAccessFile(filename, "rw").getChannel()) {
                CharBuffer cb = fc.map(MapMode.READ_WRITE, 0, numCount * 2).asCharBuffer();
                cb.put((char) 1);
                for (int i = 1; i < numCount; i++) {
                    cb.put(cb.get(i - 1));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
