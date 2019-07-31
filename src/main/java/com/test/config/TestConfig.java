package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Demo class
 *
 * @author junqiang.xiao
 * @date 2019/5/22 上午10:14
 */
@Configuration
public class TestConfig {
    @Bean
    TestInterface testInterface() {
        return new TestImpl();
    }
}
