/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.enumeration
 * @file EnumMaps.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 下午1:53:07
 * @version 1.0
 */
package name.zuoguoqing.enumeration;

import java.util.EnumMap;


interface Command {
    void action();
}



/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 下午1:53:07
 */
public class EnumMaps {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> map = new EnumMap<>(AlarmPoints.class);
        map.put(AlarmPoints.BATHROOM, () -> {
            System.out.println("Bathroom alarm");
        });
        
        map.put(AlarmPoints.OFFICE1, () -> {
            System.out.println("Office1 alarm");
        });
        
        map.forEach((a, c) -> {
            System.out.print(a + " : ");
            c.action();
        });
        
        map.keySet().forEach(System.out::println);
    }

}
