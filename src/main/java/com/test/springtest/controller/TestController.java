package com.test.springtest.controller;

import com.test.springtest.rabbitmq.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    Runner runner;

    @RequestMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public void completeHomework() {
        try {
            runner.run("Hello delay message");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
