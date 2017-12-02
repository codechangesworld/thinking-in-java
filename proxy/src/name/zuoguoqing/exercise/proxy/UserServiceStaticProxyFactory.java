/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.exercise.proxy
 * @file UserServiceStaticProxFactory.java
 * @author zuoguoqing
 * @date 2017年11月22日
 * @version 
 */
package name.zuoguoqing.exercise.proxy;

import name.zuoguoqing.exercise.service.UserService;
import name.zuoguoqing.exercise.service.impl.UserServiceImpl;

/**
 * @author zuoguoqing
 *
 */
public class UserServiceStaticProxyFactory {
    /**
     * 
     * @return
     */
    public static UserService getInstance() {
        return new UserServiceStaticProxy(new UserServiceImpl());
    }
}
