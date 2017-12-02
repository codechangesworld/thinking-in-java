/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.exercise.proxy
 * @file DynamicProxyFactory.java
 * @author zuoguoqing
 * @date 2017年11月22日
 * @version 
 */
package name.zuoguoqing.exercise.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author zuoguoqing
 *
 */
public class UserServiceDynamicProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> clazz) {
        T result = null;

        try {
            T target = clazz.newInstance();
            InvocationHandler handler = new UserServiceInvocationHandler(target);
            result = (T) Proxy.newProxyInstance(UserServiceDynamicProxyFactory.class.getClassLoader(),
                    target.getClass().getInterfaces(), handler);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;

    }
}
