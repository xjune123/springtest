package com.test.springtest.lambda;

import java.util.Arrays;

/**
 * Demo class
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/6/13
 */
public class LambdaMain {
    public static void main(String[] args) {
        Arrays.asList("a", "b", "d").forEach(f -> {

            System.out.print(f);

        });


    }
}
