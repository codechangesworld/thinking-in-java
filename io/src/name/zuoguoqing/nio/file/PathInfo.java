/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.nio.file
 * @file PathInfo.java
 * @author ZuoGuoqing
 * @date 2020年9月29日 下午2:05:56
 * @version 1.0
 */
package name.zuoguoqing.nio.file;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年9月29日 下午2:05:56
 */
public class PathInfo {
    static void show(String id, Object object) {
        System.out.println(id + " ： " + object);
    }
    
    static void info(Path path) {
        show("toString", path.toString());
        show("Exists", Files.exists(path));
        show("RegularFile", Files.isRegularFile(path));
        show("Directory", Files.isDirectory(path));
        show("Absolute", path.isAbsolute());
        show("FileName", path.getFileName());
        show("Parent", path.getParent());
        show("Root", path.getRoot());
        
        System.out.println("************************");
    }

    /**
     * @param args abc
     */
    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        
        info(Paths.get("C:", "path", "to", "nowhere", "NoFile.txt"));
        
        Path path1 = Paths.get("src\\name\\zuoguoqing\\nio\\file\\PathInfo.java");
        info(path1);
        
        Path path2 = path1.toAbsolutePath();
        info(path2);
        info(path2.getParent());
        
        try {
            info(path1.toRealPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        URI u = path1.toUri();
        System.out.println("URI : " + u);
        Path path3 = Paths.get(u);
        System.out.println(Files.exists(path3));
        File file = path2.toFile();
        System.out.println(file.length());
    }

}
