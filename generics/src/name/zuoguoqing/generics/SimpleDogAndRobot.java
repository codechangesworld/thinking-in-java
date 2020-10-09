/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.generics
 * @file SimpleDogAndRobot.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 上午10:36:24
 * @version 1.0
 */
package name.zuoguoqing.generics;

/** 
 * Java潜在类型机制--使用接口实现
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 上午10:36:24
 */
public class SimpleDogAndRobot {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Communicate.perform(new PerformDog());
        Communicate.perform(new PerformRobot());
    }

}

interface Perform{
    void speak();
    void sit();
}

class PerformDog implements Perform {

    @Override
    public void speak() {
        System.out.println("Woof!");
    }

    @Override
    public void sit() {
        System.out.println("Sitting!");
    }
    
    public void reproduce() {
        
    }
    
}

class PerformRobot implements Perform {

    @Override
    public void speak() {
        System.out.println("Click!");
    }

    @Override
    public void sit() {
        System.out.println("Clank!");
    }
    
    public void oilChange() {}
}

class Communicate {
    static <T extends Perform> void perform(T t) {
        t.speak();
        t.sit();
    }
}

