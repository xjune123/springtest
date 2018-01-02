package com.test.springtest.prioritymq;

import com.test.springtest.SpringtestApplication;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SendPriorityMessage implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    private final ConfigurableApplicationContext context;

    public SendPriorityMessage(RabbitTemplate rabbitTemplate,
                               ConfigurableApplicationContext context) {
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        System.out.println("SendTime :" + new Date());
        String messageText = "";

        //第一条消息
        for (int i = 0; i < 100; i++) {
            MessageProperties properties = new MessageProperties();
            if (i % 10 == 0) {
                properties.setPriority(20);
            } else {
                properties.setPriority(10);

            }
            properties.setContentType(MediaType.APPLICATION_JSON_VALUE);
            properties.setHeader(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            messageText = "我是第 " + i + " 条消息:" + properties.getPriority();
            rabbitTemplate.send(SpringtestApplication.PRIORITY_EXCHANGE, SpringtestApplication.PRIORITY_QUEUE_NAME, MessageBuilder.withBody(messageText.getBytes()).andProperties(properties).build());

        }


    }
}
