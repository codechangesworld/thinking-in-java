/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.exercise.string
 * @file PattenMatcher.java
 * @author zuoguoqing
 * @date 2017年11月18日
 * @version 
 */
package name.zuoguoqing.exercise.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zuoguoqing
 *
 */
public class PattenMatcher {

    public static void m1() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher("我的QQ是:456456 我的电话是:0532214 我的邮箱是:aaa123@aaa.com"); 
        
        while (matcher.find()) {
            System.out.println("group count: " + matcher.groupCount());
            System.out.println("group:       " + matcher.group());
            System.out.println("start:       " + matcher.start());
            System.out.println("end:         " + matcher.end());
        }
    }
    
    /**
     * 捕获组
     */
    public static void m2() {
        Pattern pattern = Pattern.compile("([a-z]+)([0-9]+)");
        Matcher matcher = pattern.matcher("abcac1234dsf888dd");
        
        while (matcher.find()) {
            System.out.println("group count: " + matcher.groupCount());
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("group:       " + matcher.group(i));
                System.out.println("start:       " + matcher.start(i));
                System.out.println("end:         " + matcher.end(i));
            }
        }
    }
    
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        m2();

    }

}
