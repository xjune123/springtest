package com.test.springdegin.proxy.cglib;

public class CglibOrderImpl {
    public String  order(String orderNumber) {
        System.out.println("下订单:" + orderNumber);
        return "Success";
    }
}  