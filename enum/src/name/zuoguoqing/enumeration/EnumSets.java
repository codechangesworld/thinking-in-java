/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.enumeration
 * @file EnumSets.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 下午1:39:37
 * @version 1.0
 */
package name.zuoguoqing.enumeration;

import java.util.EnumSet;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 下午1:39:37
 */
public class EnumSets {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EnumSet<AlarmPoints> set = EnumSet.noneOf(AlarmPoints.class);
        System.out.println(set);
        set.add(AlarmPoints.BATHROOM);
        System.out.println(set);
        set.addAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));
        System.out.println(set);
        set = EnumSet.allOf(AlarmPoints.class);
        System.out.println(set);
        set.removeAll(EnumSet.of(AlarmPoints.STAIR1, AlarmPoints.STAIR2, AlarmPoints.KITCHEN));
        System.out.println(set);
        set.removeAll(EnumSet.range(AlarmPoints.OFFICE1, AlarmPoints.OFFICE4));
        System.out.println(set);
        set = EnumSet.complementOf(set);
        System.out.println(set);
    }

}
