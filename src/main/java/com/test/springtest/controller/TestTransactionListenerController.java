package com.test.springtest.controller;

import com.test.springtest.transactionmq.TransactionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestTransactionListenerController {
    @Autowired
    TransactionUserService transactionUserService;

    @RequestMapping("/testTransactionListener")
    @ResponseStatus(HttpStatus.OK)
    public void testTranasction() {
        transactionUserService.insertUser();
    }

}
