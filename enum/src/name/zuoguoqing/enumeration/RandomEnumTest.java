/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.enumeration
 * @file RandomEnumTest.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 下午1:09:48
 * @version 1.0
 */
package name.zuoguoqing.enumeration;

import java.util.stream.IntStream;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 下午1:09:48
 */
public class RandomEnumTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        IntStream.range(0, 20).forEach(i -> {
            System.out.println(Enums.random(Activaty.class));
        });

    }

}

enum Activaty {
    SITTING, LYING, STANDING, HOPPING,
    RUNNING, DODGING, JUMPING, FALLING, FLYING
}
