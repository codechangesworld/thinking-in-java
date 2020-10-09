/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.generics
 * @file LatentReflection.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 上午10:43:54
 * @version 1.0
 */
package name.zuoguoqing.generics;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 上午10:43:54
 */
public class LatentReflection {

    /**
     * @param args
     */
    public static void main(String[] args) {
        CommunicateReflectively.perform(new Mine());
        CommunicateReflectively.perform(new SmartDog());
        CommunicateReflectively.perform(new PerformRobot());
    }

}

// 没有继承接口
class Mine {
    public void walkAgainstTheWind() {
        System.out.println("walk against the wind");
    }

    public void sit() {
        System.out.println("Pretending to sit");
    }

    public void pushInvisibleWalls() {
        System.out.println("push the invisible walls");
    }

    @Override
    public String toString() {
        return "Mime";
    }
}

// 没有继承接口
class SmartDog {
    public void speak() { System.out.println("Woof!"); }
    public void sit() { System.out.println("Sitting"); }
    public void reproduce() {}
}

// 测试方法
class CommunicateReflectively {
    static void perform(Object performer) {
        Class<?> cls = performer.getClass();
        
        try {
            try {
                cls.getMethod("speak").invoke(performer);
            } catch (NoSuchMethodException e) {
                System.out.println(performer + " cannot speak");
            }
            
            try {
                cls.getMethod("sit").invoke(performer);
            } catch (NoSuchMethodException e) {
                System.out.println(performer + " cannot sit");
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | SecurityException e) {
            throw new RuntimeException(performer.toString(), e);
        }
    }
}
