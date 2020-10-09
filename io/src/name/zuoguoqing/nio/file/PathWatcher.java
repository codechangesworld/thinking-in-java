/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.nio.file
 * @file PathWatcher.java
 * @author ZuoGuoqing
 * @date 2020年9月29日 下午4:46:35
 * @version 1.0
 */
package name.zuoguoqing.nio.file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.concurrent.Executors;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年9月29日 下午4:46:35
 */
public class PathWatcher {
    private static Path testPath = Paths.get("test");

    static void deleteTxtFiles() {
        try {
            Files.walk(testPath).filter(file -> file.toString().endsWith(".txt")).forEach(t -> {
                try {
                    System.out.println("*** deleting " + t);
                    Files.delete(t);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void watchDir(Path dir) {
        try {
            WatchService service = FileSystems.getDefault().newWatchService();
            dir.register(service, StandardWatchEventKinds.ENTRY_DELETE);
            Executors.newSingleThreadExecutor().submit(() -> {
                WatchKey key;
                try {
                    key = service.take();
                    for (WatchEvent<?> event : key.pollEvents()) {
                        System.out.println("event.context : " + event.context());
                        System.out.println("event.count : " + event.count());
                        System.out.println("event.kind : " + event.kind());
                    }
//                    throw new InterruptedException();
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Directories.refreshTestDir();
        Directories.populateTestDir();
        Path fPath = Files.createFile(testPath.resolve("Hello.txt"));
        Files.createFile(testPath.resolve("Hello2.txt"));

        Files.write(fPath, "hello world!\nNice to meet you!\n".getBytes());
        Files.write(fPath, Arrays.asList(new String[] { "hello", "iterable" }),
                StandardOpenOption.APPEND);

        Files.walk(testPath).filter(Files::isDirectory).forEach(PathWatcher::watchDir);

        deleteTxtFiles();
        System.exit(0);
    }

}
