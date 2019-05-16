package com.test.springtest.test;


import org.apache.commons.lang3.StringUtils;

/**
 * Demo class
 *
 * @author junqiang.xiao
 * @date 2019/5/6 下午3:12
 */
public class StringSpilt {
    public static void main(String[] args) {
        String a= "1222";
        String[] b= StringUtils.split(a,",");
        System.out.println(b);
    }
}
