package com.test.springtest.testbasic;

/**
 * Demo class
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/6/13
 */
public class TestThreadMain {

    public static void main(String[] args) throws InterruptedException {
        CreateThread a = new CreateThread("A");
        Thread at = new Thread(a);
        CreateThread b = new CreateThread("B");
        Thread bt = new Thread(b);

        //开启线程，并执行该线程的run方法
        at.start();
        //仅仅是对象调用方法。而线程创建了，并没有运行。
       // at.run();
        at.join();
        /**join的意思是使得放弃当前线程的执行，并返回对应的线程，例如下面代码的意思就是：
         程序在main线程中调用at线程的join方法，则main线程放弃cpu控制权，并返回at线程继续执行直到线程at执行完毕
         所以结果是at线程执行完后，才到主线程执行，相当于在main线程中同步at线程，at执行完了，main线程才有执行的机会
         */
        bt.start();


        for (int x = 0; x < 60; x++) {
            System.out.println("Hello word !-----" + x);
        }
    }


}
