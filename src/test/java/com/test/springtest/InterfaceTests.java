package com.test.springtest;

import com.test.config.TestConfig;
import com.test.config.TestInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Demo class
 *
 * @author junqiang.xiao
 * @date 2019/5/22 上午10:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringtestApplication.class})
/**
 * configuration 因为componentScan扫描到了，才会给spring 管理bean 若没有扫描到，需要使用import或者改变componentScan范围
 */
@Import(TestConfig.class)
public class InterfaceTests {
    @Autowired
    TestInterface testInterface;
    @Test
    public void testTransaction() {
        testInterface.test();
    }
}
