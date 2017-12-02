/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.exercise.proxy
 * @file UserServiceStaticProxy.java
 * @author zuoguoqing
 * @date 2017年11月22日
 * @version 
 */
package name.zuoguoqing.exercise.proxy;

import name.zuoguoqing.exercise.model.User;
import name.zuoguoqing.exercise.service.UserService;

/**
 * @author zuoguoqing
 *
 */
public class UserServiceStaticProxy implements UserService {

    private UserService target;

    public UserServiceStaticProxy(UserService object) {
        target = object;
    }

    @Override
    public boolean save(User user) {

        System.out.println("------------- before proxy ---------");

        boolean result = target.save(user);

        System.out.println("------------- after proxy ----------");

        return result;
    }

    @Override
    public User getById(String id) {

        System.out.println("------------- before proxy ---------");

        User result = target.getById(id);

        System.out.println("------------- after proxy ----------");

        return result;
    }

}
