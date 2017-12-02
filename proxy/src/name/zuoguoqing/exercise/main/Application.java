/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.exercise.main
 * @file Application.java
 * @author zuoguoqing
 * @date 2017年11月22日
 * @version 
 */
package name.zuoguoqing.exercise.main;

import name.zuoguoqing.exercise.model.User;
import name.zuoguoqing.exercise.proxy.UserServiceCglibProxyFactory;
import name.zuoguoqing.exercise.proxy.UserServiceDynamicProxyFactory;
import name.zuoguoqing.exercise.proxy.UserServiceStaticProxyFactory;
import name.zuoguoqing.exercise.service.UserService;
import name.zuoguoqing.exercise.service.impl.UserServiceImpl;

/**
 * @author zuoguoqing
 *
 */
public class Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        long begin = System.currentTimeMillis();

//        staticProxyTest();
        
//        jdkProxyTest();
        
        cglibProxyTest();
        
        long end = System.currentTimeMillis();
        
        System.out.println("total time cost: " + (end - begin) + " ms.");
    }
    
    public static void hashCodeTest() {
        User user1 = new User();
        User user2 = new User();
        System.out.println("user1.equals(user2): " + user1.equals(user2));
        System.out.println("user1.hashCode(): " + user1.hashCode());
        System.out.println("user2.hashCode(): " + user2.hashCode());
        
        user1.setId("1001");
        user2.setId("1001");
        System.out.println("user1.equals(user2): " + user1.equals(user2));
        System.out.println("user1.hashCode(): " + user1.hashCode());
        System.out.println("user2.hashCode(): " + user2.hashCode()); 
    }

    /**
     * 
     */
    public static void staticProxyTest() {
        UserService staticProxy = UserServiceStaticProxyFactory.getInstance();
        staticProxy.save(new User());
        staticProxy.getById("1001");
    }

    /**
     * 
     */
    public static void jdkProxyTest() {
//        UserService target = new UserServiceImpl();
//        InvocationHandler handler = new UserServiceInvocationHandler(target);
//        
//        UserService userService = (UserService) Proxy.newProxyInstance(
//                Application.class.getClassLoader(), new Class[] { UserService.class },
//                handler);
        
        UserService userService = UserServiceDynamicProxyFactory.getInstance(UserServiceImpl.class);
        
        userService.save(new User());
        userService.getById(new String());
    }
    
    /**
     * 
     */
    public static void cglibProxyTest() {
        UserService userService = UserServiceCglibProxyFactory.getInstance(UserServiceImpl.class);
        
        userService.save(new User());
        userService.getById(new String());
    }

}
