/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.io
 * @file DirList3.java
 * @author ZuoGuoqing
 * @date 2017年11月24日 下午11:17:51
 * @version 1.0
 */
package name.zuoguoqing.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2017年11月24日 下午11:17:51
 */
public class DirList3 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String regex = "D.*\\.java";
        File path = new File(".\\src\\name\\zuoguoqing\\io");
        String[] names;
        
        names = path.list();
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
        System.out.println("All Files:   \t" + Arrays.toString(names));
        
        names = path.list(new FilenameFilter() {
            
            @Override
            public boolean accept(File dir, String name) {
                return Pattern.matches(regex, name);
            }
        });
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Matched Files:\t" + Arrays.toString(names));
        
    }

}
