/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.enumeration
 * @file Enums.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 下午1:02:49
 * @version 1.0
 */
package name.zuoguoqing.enumeration;

import java.util.Random;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 下午1:02:49
 */
public class Enums {
    private static Random rand = new Random(47);
    
    public static <T extends Enum<T>> T random(Class<T> cls) {
        return random(cls.getEnumConstants());
    }
    
    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }

}
