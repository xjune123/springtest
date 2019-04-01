package com.test.springdegin.proxy.jdkproxy;

/**
 * Demo class
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/6/7
 */
public class OrderImpl implements IOrder {
    @Override
    public void order(String orderNumber) {
        System.out.println("下订单:"+orderNumber);
    }
}
