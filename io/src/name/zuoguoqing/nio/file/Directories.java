/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.nio.file
 * @file Directories.java
 * @author ZuoGuoqing
 * @date 2020年9月29日 下午2:51:51
 * @version 1.0
 */
package name.zuoguoqing.nio.file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年9月29日 下午2:51:51
 */
public class Directories {
    private static Path testPath = Paths.get("test");
    private static String sep = FileSystems.getDefault().getSeparator();
    private static List<String> parts = Arrays.asList("foo", "bar", "baz", "bag");

    /**
     * 生成变动目录
     * 
     * @return
     */
    static Path makeVariant() {
        Collections.rotate(parts, 1);
        return Paths.get("test", String.join(sep, parts));
    }

    /**
     * 刷新测试目录
     * 
     * @throws Exception
     */
    static void refreshTestDir() throws Exception {
        if (Files.exists(testPath)) {
            removeDirectories(testPath);
        } 
        if (! Files.exists(testPath)) {
            Files.createDirectory(testPath);
        }
    }

    /**
     * 删除目录和子目录
     * 
     * @param path
     * @throws IOException
     */
    static void removeDirectories(Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                    throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        refreshTestDir();
        Files.createFile(testPath.resolve("Hello.txt"));
        Path variant = makeVariant();

        try {
            Files.createDirectory(variant);
        } catch (Exception e) {
            System.out.println("目录层级太多，使用另一个方法创建多级目录！");
        }

        populateTestDir();
        Path tempDir = Files.createTempDirectory(testPath, "Dir_");
        Files.createTempFile(tempDir, "prefix", ".non");
        Files.newDirectoryStream(testPath).forEach(System.out::println);
        System.out.println("********************");
        Files.walk(testPath).forEach(System.out::println);

    }

    /**
     * 填充测试目录
     * @throws Exception
     */
    static void populateTestDir() throws Exception {
        for (int i = 0; i < parts.size(); i++) {
            Path variant = makeVariant();
            if (!Files.exists(variant)) {
                Files.createDirectories(variant);
                Files.copy(
                        Paths.get("src", "name", "zuoguoqing", "nio", "file", "Directories.java"),
                        variant.resolve("File.txt"));
                Files.createTempFile(variant, null, null);
            }
        }
    }

}
