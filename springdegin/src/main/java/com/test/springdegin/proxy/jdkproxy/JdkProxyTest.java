package com.test.springdegin.proxy.jdkproxy;

/**
 * Demo class
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/6/7
 */
public class JdkProxyTest {
    public static void main(String[] args) {
        IOrder order = new OrderImpl();
        OrderProxy orderProxy = new OrderProxy();
        IOrder object = (IOrder) orderProxy.bind(order);
        object.order("123567");
     }
}
