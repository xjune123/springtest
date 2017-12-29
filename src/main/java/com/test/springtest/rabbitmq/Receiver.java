package com.test.springtest.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
@Component
public class Receiver implements MessageListener{
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(Message message) {
        System.out.println("ReceiveTime :"+new Date());

        System.out.println("Received <" + message  + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("ReceiveTime :"+new Date());

        System.out.println("Received <" + message  + ">");
        latch.countDown();
    }
}
