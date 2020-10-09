/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.generics
 * @file DogsAndRobotMethodReferences.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 上午10:58:03
 * @version 1.0
 */
package name.zuoguoqing.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 上午10:58:03
 */
public class DogsAndRobotMethodReferences {

    /**
     * @param args
     */
    public static void main(String[] args) {
        CommunicateFuncitional.perform(new DogA(), DogA::speak, DogA::sit);
        CommunicateFuncitional.perform(new RobotA(), RobotA::speak, RobotA::sit);
        CommunicateFuncitional.perform(new Mine(), Mine::walkAgainstTheWind, Mine::pushInvisibleWalls);
        
        List<Consumer<DogA>> list = new ArrayList<Consumer<DogA>>();
        list.add(DogA::sit);
        list.add(DogA::speak);
        CommunicateFuncitional.perform(new DogA(), list);
    }

}

class DogA {

    public void speak() {
        System.out.println("Woof!");
    }

    public void sit() {
        System.out.println("Sitting!");
    }
    
    public void reproduce() {
        
    }
    
}

class RobotA {

    public void speak() {
        System.out.println("Click!");
    }

    public void sit() {
        System.out.println("Clank!");
    }
    
    public void oilChange() {}
}

class CommunicateFuncitional {
    public static <P> void perform(P performer, Consumer<P> c1, Consumer<P> c2) {
        c1.accept(performer);
        c2.accept(performer);
    }
    
    public static <P> void perform(P performer, List<Consumer<P>> c) {
        c.forEach(e -> e.accept(performer));
    }
}
