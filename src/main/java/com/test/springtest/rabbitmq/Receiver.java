package com.test.springtest.rabbitmq;

import com.test.springtest.SpringtestApplication;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = SpringtestApplication.queueName)
    public void receiveMessage(Message message) {
        System.out.println("RabbitListener ReceiveTime :" + new Date());

        System.out.println("Received <" + message.toString() +  ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
