/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.io
 * @file DirList1.java
 * @author ZuoGuoqing
 * @date 2017年11月24日 下午10:52:02
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
 * @date 2017年11月24日 下午10:52:02
 */
public class DirList1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String regex = "D.*\\.java";
        File path = new File(".\\src\\name\\zuoguoqing\\io");
        String[] names;
        
        names = path.list();
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(names));
        
        names = path.list(new DirFilter(regex));
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(names));
    }
    
    static class DirFilter implements FilenameFilter {
        Pattern pattern;
        public DirFilter(String regex) {
            pattern = Pattern.compile(regex);
        }
        
        @Override
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
        
    }

}
