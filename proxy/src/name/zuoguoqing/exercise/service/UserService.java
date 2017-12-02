/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.exercise.service
 * @file UserService.java
 * @author zuoguoqing
 * @date 2017年11月22日
 * @version 
 */
package name.zuoguoqing.exercise.service;

import name.zuoguoqing.exercise.model.User;

/**
 * @author zuoguoqing
 *
 */
public interface UserService {
    
    /**
     * 保存用户数据
     * @return
     */
    boolean save(User user);
    
    /**
     * 获取用户信息
     * @param id 用户id
     * @return
     */
    User getById(String id);
}
