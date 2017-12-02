/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.exercise.proxy
 * @file JDKProxy.java
 * @author zuoguoqing
 * @date 2017年11月22日
 * @version 
 */
package name.zuoguoqing.exercise.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zuoguoqing
 *
 */
public class UserServiceInvocationHandler implements InvocationHandler {
    
    private Object target;
    
    public UserServiceInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    /* (non-Javadoc)
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        System.out.println("------------- before proxy ---------");

        Object result = method.invoke(target, args);

        System.out.println("------------- after proxy ----------");

        return result;
    }

}
