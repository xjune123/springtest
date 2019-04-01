package com.test.springdegin.proxy.cglib;

/**
 * Cglib测试
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/6/7
 */
public class CglibTest {
    public static void main(String[] args) {
        CglibOrderImpl cglibOrderImpl = new CglibOrderImpl();
        OrderCglib cglib = new OrderCglib();
        CglibOrderImpl cglibOrder1 = (CglibOrderImpl) cglib.getInstance(cglibOrderImpl);
        cglibOrder1.order("123");

    }
}
