package com.test.springtest.test;

import java.math.BigDecimal;

/**
 * Demo class
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/3/28
 */
public class TestMain {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = new User();
        User user2 = new User();
        user.setName("Jack");
        user.setUserId(1L);
        user.setSalary(BigDecimal.valueOf(10000.1));
        user.setBonus(10000.002);
        user2 = userService.caculate(user);
        System.out.println(user);
        System.out.println(user2);
        System.out.println(user.equals(user2));
        //Double类型计算不准确

        Integer x = 100;
        triple(100);
        //传参是传值
        System.out.println(x);
        System.out.println(4.015*100);

    }

    public static void triple(Integer x) {
        x = x + 1;
    }
}
