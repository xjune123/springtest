package com.test.springdegin.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/6/7
 */
public class OrderProxy implements InvocationHandler {
    //这其实业务实现类对象，用来调用具体的业务方法
    private Object target;

    public Object bind(Object target) {
        //接收业务实现类对象参数
        this.target = target;

        //通过反射机制，创建一个代理类对象实例并返回。用户进行方法调用时使用
        //创建代理对象时，需要传递该业务类的类加载器（用来获取业务实现类的元数据，在包装方法是调用真正的业务方法）、接口、handler实现类
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("执行之前:" + args.toString());
        result = method.invoke(target, args);
        System.out.println("执行之后:" + args.toString());
        return result;
    }
}
