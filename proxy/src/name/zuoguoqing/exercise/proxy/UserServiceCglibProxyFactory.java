/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.exercise.proxy
 * @file UserServiceCglibProxyFactory.java
 * @author zuoguoqing
 * @date 2017年11月22日
 * @version 
 */
package name.zuoguoqing.exercise.proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author zuoguoqing
 *
 */
public class UserServiceCglibProxyFactory {
    
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> superclass) {
        T result = null;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(superclass);
        enhancer.setCallback(new UserServiceInterceptor());
        result = (T) enhancer.create();
        
        return result;
    }
}
