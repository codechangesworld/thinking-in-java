/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.exercise.string
 * @file StringTest.java
 * @author ZuoGuoqing
 * @date 2020年9月29日 下午8:15:50
 * @version 1.0
 */
package name.zuoguoqing.exercise.string;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年9月29日 下午8:15:50
 */
public class StringTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String internStr1 = "test about intern";
        String internStr2 = new String("test about intern");
        String internStr3 = internStr1.intern();
        String internStr4 = "test about intern";
        
        System.out.println(internStr1 == internStr2);
        System.out.println(internStr1 == internStr3);
        System.out.println(internStr2 == internStr3);
        
        System.out.println(internStr4.codePointAt(5));
        System.out.println(internStr4.codePointBefore(1));
        System.out.println(internStr4.codePointCount(0, 3));
        
    }

}
