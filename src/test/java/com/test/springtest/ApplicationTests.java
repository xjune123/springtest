package com.test.springtest;

import com.test.springtest.transaction.service.TransactionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringtestApplication.class})
public class ApplicationTests {
    @Autowired
    private TransactionService transactionalService;

    @Test
    public void testTransaction() {
        transactionalService.insertAsync();
    }

    @Before
    public void testBefore() {
        System.out.println("before");
    }

    @After
    public void testAfter() {
        System.out.println("after");
    }
}
