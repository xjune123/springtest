package com.test.springtest.controller;

import com.test.springtest.transaction.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestTransactionController {
    @Autowired
    TransactionalService transactionalService;

    @RequestMapping("/testTranasction")
    @ResponseStatus(HttpStatus.OK)
    public void testTranasction() {
        transactionalService.insert();
    }

    @RequestMapping("/insertBatch")
    @ResponseStatus(HttpStatus.OK)
    public void insertBatch() {
        transactionalService.insertBatch();
    }

    @RequestMapping("/insertAsync")
    @ResponseStatus(HttpStatus.OK)
    public void insertAsync() {
        transactionalService.insertAsync();
    }

    @RequestMapping("/insertOkAsync")
    @ResponseStatus(HttpStatus.OK)
    public void insertOkAsync() {
        transactionalService.insertOkAsync();
    }

    @RequestMapping("/standardInsertBatch")
    @ResponseStatus(HttpStatus.OK)
    public void standardInsertBatch() {
        transactionalService.standardInsertBatch();
    }

    @RequestMapping("/insertRequiresNew")
    @ResponseStatus(HttpStatus.OK)
    public void insertRequiresNew() {
        transactionalService.insertRequiresNew();
    }
}
