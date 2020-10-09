/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.nio.file
 * @file FileSystemDemo.java
 * @author ZuoGuoqing
 * @date 2020年9月29日 下午4:24:49
 * @version 1.0
 */
package name.zuoguoqing.nio.file;

import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年9月29日 下午4:24:49
 */
public class FileSystemDemo {
    static void show(String id, Object object) {
        System.out.println(id + " : " + object);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        FileSystem fs = FileSystems.getDefault();
        for (FileStore fst : fs.getFileStores()) {
            show("FileStore", fst);
        }
        for (Path root : fs.getRootDirectories()) {
            show("Root", root);
        }
        show("Separator", fs.getSeparator());
        show("UserPrincipalLookupService", fs.getUserPrincipalLookupService());
        show("isOpen", fs.isOpen());
        show("isReadOnly", fs.isReadOnly());
        show("provider", fs.provider());
        show("supportedFileAttributeViews", fs.supportedFileAttributeViews());
    }

}
