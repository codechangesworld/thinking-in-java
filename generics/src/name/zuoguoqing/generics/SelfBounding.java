/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.generics
 * @file SelfBounding.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 上午10:16:57
 * @version 1.0
 */
package name.zuoguoqing.generics;

/** 
 * 自限定类型
 * 
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 上午10:16:57
 */
public class SelfBounding {

    /**
     * @param args
     */
    public static void main(String[] args) {
        A a = new A();
        a.set(new A());
        a = a.get();
        a = a.set(new A()).get();
        C c = new C();
        c = c.setAndGet(new C());
    }

}

/**
 * 自限定类型基类
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 上午10:19:45
 */
class SelfBounded<T extends SelfBounded<T>> {
    T e;
    
    SelfBounded<T> set(T e) {
        this.e = e;
        return this;
    }
    
    T get() {
        return e;
    }
}

/**
 * 限定子类中元素类型为子类自身
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 上午10:19:56
 */
class A extends SelfBounded<A> {}
/**
 * 限定子类中元素类型为其它子类
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 上午10:21:30
 */
class B extends SelfBounded<A> {}

class C extends SelfBounded<C> {
    C setAndGet(C e) {
        return set(e).get();
    }
}

class D {}

//error!!!
//class E extends SelfBounded<D>{}

@SuppressWarnings("rawtypes")
class F extends SelfBounded {}

