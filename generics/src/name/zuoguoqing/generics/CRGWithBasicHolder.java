/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.generics
 * @file CRGWithBasicHolder.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 上午10:07:41
 * @version 1.0
 */
package name.zuoguoqing.generics;

/** 
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 上午10:07:41
 */
public class CRGWithBasicHolder {
    static class BasicHolder<T> {
        T e;
        
        void set(T e) {
            this.e = e;
        }
        
        T get() {
            return e;
        }
        
        void classNameOfT() {
            System.out.println(e.getClass().getSimpleName());
        }
    }
    
    static class SubType extends BasicHolder<SubType> {
        
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SubType subType = new SubType();
        subType.set(new SubType());
        @SuppressWarnings("unused")
        SubType subType2 = subType.get();
        subType.classNameOfT();
    }

}
