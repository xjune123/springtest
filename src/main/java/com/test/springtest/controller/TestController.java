package com.test.springtest.controller;

import com.test.springtest.dlxmessage.SendDelayMessage;
import com.test.springtest.rabbitmq.Runner;
import com.test.springtest.redismq.SendRedisDelayQueue;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
public class TestController {
    @Autowired
    Runner runner;
    @Autowired
    SendDelayMessage sendDelayMessage;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Autowired
    SendRedisDelayQueue sendRedisDelayQueue;

    @RequestMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public void completeHomework() {
        try {
            runner.run("Hello delay message");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/test/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void testVar(@PathVariable String id) {
        System.out.println("test/id");
    }

    @RequestMapping("/test/abc")
    @ResponseStatus(HttpStatus.OK)
    public void testVar() {
        System.out.println("test abc");
    }


    @RequestMapping("/testDelay")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity testDelay() throws IOException {
        sendDelayMessage.send();
        System.out.println(new Date());
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/testDelay2")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity testDelay2() throws IOException {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
//        声明消息处理器  这个对消息进行处理  可以设置一些参数   对消息进行一些定制化处理   我们这里  来设置消息的编码  以及消息的过期时间  因为在.net 以及其他版本过期时间不一致   这里的时间毫秒值 为字符串
        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
//            设置编码
            messageProperties.setContentEncoding("utf-8");
//            设置过期时间10*1000毫秒
            //messageProperties.setExpiration("5000");
            return message;
        };
//         向DL_QUEUE 发送消息  10*1000毫秒后过期 形成死信
        rabbitTemplate.convertAndSend("DL_EXCHANGE", "DL_KEY", "hello1  5000", messagePostProcessor, correlationData);
        MessagePostProcessor messagePostProcessor2 = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
//            设置编码
            messageProperties.setContentEncoding("utf-8");
//            设置过期时间10*1000毫秒
            messageProperties.setExpiration("10000");
            return message;
        };
        rabbitTemplate.convertAndSend("DL_EXCHANGE", "DL_KEY", "hello2  10000", messagePostProcessor2, correlationData);
        MessagePostProcessor messagePostProcessor3 = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
//            设置编码
            messageProperties.setContentEncoding("utf-8");
//            设置过期时间10*1000毫秒
            messageProperties.setExpiration("15000");
            return message;
        };
        rabbitTemplate.convertAndSend("DL_EXCHANGE", "DL_KEY", "hello3 15000", messagePostProcessor3, correlationData);

        System.out.println(new Date());
        return ResponseEntity.ok().build();

    }

    @RequestMapping("/testRedisDelay")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity testRedisDelay(String orderId, @RequestParam(defaultValue = "10") Long delayTime) throws IOException {
        sendRedisDelayQueue.Send(orderId, delayTime);
        System.out.println(new Date());
        return ResponseEntity.ok().build();
    }
}
