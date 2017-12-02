/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.exercise.service.impl
 * @file UserServiceImpl.java
 * @author zuoguoqing
 * @date 2017年11月22日
 * @version 
 */
package name.zuoguoqing.exercise.service.impl;

import name.zuoguoqing.exercise.model.User;
import name.zuoguoqing.exercise.service.UserService;

/**
 * @author zuoguoqing
 *
 */
public class UserServiceImpl implements UserService {

    @Override
    public boolean save(User user) {
        // TODO 保存用户信息到数据库
        System.out.println("-------- save user info --------");
        
        return true;
    }

    @Override
    public User getById(String id) {
        // TODO 从数据库获取用户信息
        System.out.println("-------- get user info by id ---------");
        
        return new User();
    }
    
}
