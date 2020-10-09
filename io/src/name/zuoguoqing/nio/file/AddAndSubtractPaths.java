/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.nio.file
 * @file AddAndSubtractPaths.java
 * @author ZuoGuoqing
 * @date 2020年9月29日 下午3:51:37
 * @version 1.0
 */
package name.zuoguoqing.nio.file;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年9月29日 下午3:51:37
 */
public class AddAndSubtractPaths {
    static Path base = Paths.get("..", "..").toAbsolutePath().normalize();

    static void show(int id, Path result) {
        if (result.isAbsolute()) {
            System.out.println("(" + id + ")r " + base.relativize(result));
        } else {
            System.out.println("(" + id + ") " + result);
        }
        try {
            System.out.println("toRealPath : " + result.toRealPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(base);

        Path p1 = Paths.get("src\\name\\zuoguoqing\\nio\\file\\AddAndSubtractPaths.java")
                .toAbsolutePath();
        show(1, p1);
        
        Path p2 = p1.getParent().getParent().resolve("string").resolve("..")
                .resolve(p1.getParent().getFileName());
        show(2, p2);
        show(3, p2.normalize());

        Path p3 = Paths.get("..", "..");
        show(4, p3);
        show(4, p3.normalize());
        show(5, p3.toAbsolutePath().normalize());
        
        Path p4 = Paths.get(".").toAbsolutePath().resolve(p3);
        show(6, p4);
        show(7, p4.normalize());
        
        Path p5 = Paths.get("").toAbsolutePath();
        show(8, p5);
        show(9, p5.resolve("test").resolveSibling("strings"));
        show(10, Paths.get("nonexist"));
    }

}
