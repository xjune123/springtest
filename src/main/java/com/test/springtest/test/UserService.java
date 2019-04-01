package com.test.springtest.test;

import java.math.BigDecimal;

/**
 * Demo class
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/3/28
 */
public class UserService {
    public User caculate(User user){
        user.setSalary(user.getSalary().add(BigDecimal.valueOf(1)));
        user.setBonus(user.getBonus()*100);
        return user;
    }
}
