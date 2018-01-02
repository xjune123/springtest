package com.test.springtest.prioritymq;

import com.test.springtest.SpringtestApplication;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

@Component
public class ReceivePriorityMessage {
    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = SpringtestApplication.PRIORITY_QUEUE_NAME)
    public void receiveMessage(Message message) {
        System.out.println("PRIORITY RabbitListener ReceiveTime :" + new Date());

        System.out.println("Received <" + message.toString() +  ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
