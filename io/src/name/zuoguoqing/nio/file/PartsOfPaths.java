/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.nio.file
 * @file PartsOfPaths.java
 * @author ZuoGuoqing
 * @date 2020年9月29日 下午2:35:49
 * @version 1.0
 */
package name.zuoguoqing.nio.file;

import java.nio.file.Path;
import java.nio.file.Paths;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年9月29日 下午2:35:49
 */
public class PartsOfPaths {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        Path path = Paths.get("src\\name\\zuoguoqing\\nio\\file\\PartsOfPaths.java").toAbsolutePath();
        for (int i = 0; i < path.getNameCount(); i++) {
            System.out.print(path.getName(i) + "   ");
        }
        System.out.println("\npath ends withs '.java' : " + path.endsWith(".java"));
        System.out.println("path to string ends withs '.java' : " + path.toString().endsWith(".java"));
        for (Path pe : path) {
            System.out.print(pe + " : ");
            System.out.print(path.startsWith(pe) + " : ");
            System.out.println(path.endsWith(pe));
        }
        System.out.println("starts with : " + path.getRoot() + " : " + path.startsWith(path.getRoot()));
        
    }

}
