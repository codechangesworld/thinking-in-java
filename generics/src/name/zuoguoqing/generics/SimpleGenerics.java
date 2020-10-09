/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.generics
 * @file SimpleGenerics.java
 * @author ZuoGuoqing
 * @date 2020年10月2日 下午9:41:00
 * @version 1.0
 */
package name.zuoguoqing.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月2日 下午9:41:00
 */
public class SimpleGenerics {
    static class Hoder<T> {
        private T e;
        
        void set(T e) {
            this.e = e;
        }
        
        T get() {
            return e;
        }
    }
    
    @SuppressWarnings("hiding")
    static class Tuple<A, B> {
        final A a;
        final B b;
        
        Tuple(A a, B b) {
            this.a = a; 
            this.b = b;
        }
        
        String rep() {
            return a + " : " + b;
        }
    }
    
    @SuppressWarnings({ "hiding" })
    static class Tuple2<A, B, C> extends Tuple<A, B> {
        final C c;
        
        Tuple2(A a, B b, C c) {
            super(a, b);
            this.c = c;
        }
        
        @Override
        String rep() {
            return a + " : " + b + " : " + c;
        }
    }
    
    static interface say<T> {
        String sayType(T t);
    }
    
    static class SaySomething<T> implements say<T> {

        @Override
        public String sayType(T t) {
            return t.getClass().getSimpleName();
        }
        
    }
    
    static class GenericMethod {
        <T> String type(T t) {
            return t.getClass().getSimpleName();
        }
        
        static <T> T show(T t) {
            return t;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Hoder<String> hoder = new Hoder<>();
        hoder.set("hello");
        System.out.println(hoder.get());
        System.out.println(hoder.getClass().getSimpleName());
        System.out.println(hoder.getClass().getTypeParameters()[0].getName());
        
        Tuple<Integer, String> tuple = new Tuple<Integer, String>(1001, "Anne");
        System.out.println(tuple.a + " : " + tuple.b);
        System.out.println(tuple.rep());
        
        Tuple2<Integer, String, Float> tuple2 = new Tuple2<Integer, String, Float>(1002, "Ailic", 88.5F);
        System.out.println(tuple2.a + " : " + tuple2.b + " : " + tuple2.c);
        System.out.println(tuple2.rep());
        Type type = tuple2.getClass().getGenericSuperclass();
        System.out.println(((ParameterizedType)type).getActualTypeArguments()[0]);
        System.out.println(((ParameterizedType)type).getActualTypeArguments()[1]);
        System.out.println(((ParameterizedType)type).getRawType());
        System.out.println(((ParameterizedType)type).getOwnerType());
        
        SaySomething<Integer> saySomething = new SaySomething<>();
        System.out.println(saySomething.sayType(1));
        System.out.println(new SaySomething<Hoder<String>>().sayType(new Hoder<>())); 
        
        GenericMethod method = new GenericMethod();
        System.out.println(method.type("hello instance"));
        System.out.println(GenericMethod.show("hello static"));
    }

}
