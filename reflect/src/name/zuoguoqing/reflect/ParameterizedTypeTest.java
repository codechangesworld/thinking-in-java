/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.reflect
 * @file ParameterizedTypeTest.java
 * @author ZuoGuoqing
 * @date 2020年10月1日 下午5:00:27
 * @version 1.0
 */
package name.zuoguoqing.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月1日 下午5:00:27
 */
public class ParameterizedTypeTest {
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Sub();
    }

}
 
class Parent {
 
}
 
class Child extends Parent {
 
}
 
class UpperGeneric {
    static class Generic<T, R> {
        T t;
        R r;
 
        protected Generic(T t, R r) {
            this.t = t;
            this.r = r;
        }
 
        void foo() {
            System.err.println(t + " " + r);
        }
    }
}
 
class Sub extends UpperGeneric.Generic<Child, Integer> {
 
    protected Sub() {
        super(new Child(), 1);
        Class<?> clazz = getClass().getSuperclass();
        System.out.println("super clazz:" + clazz);
 
        Type type = getClass().getGenericSuperclass();
        System.out.println("generic super class type:" + type);
 
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
        System.out.println("generic super class type:" + trueType);
        trueType = ((ParameterizedType) type).getActualTypeArguments()[1];
        System.out.println("type:" + trueType);
 
        trueType = ((ParameterizedType) type).getRawType();
        System.out.println("raw type:" + trueType);
 
        trueType = ((ParameterizedType) type).getOwnerType();
        System.out.println("owner type:" + trueType);
    }
}

