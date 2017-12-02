/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.model
 * @file Member.java
 * @author ZuoGuoqing
 * @date 2017年11月29日 下午2:16:55
 * @version 1.0
 */
package name.zuoguoqing.model;

import name.zuoguoqing.annotation.Constraints;
import name.zuoguoqing.annotation.DBInteger;
import name.zuoguoqing.annotation.DBString;
import name.zuoguoqing.annotation.DBTableName;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2017年11月29日 下午2:16:55
 */
@DBTableName(name = "MEMBER")
public class Member {

    @DBString(30)
    private String firstName;

    @DBString(value = 30, name = "LASTNAME")
    private String lastName;

    @DBInteger(name = "AGE")
    private int age;

    @DBString(value = 32, constraints = @Constraints(primaryKey = true, unique = true, allowNull = false))
    private String id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
