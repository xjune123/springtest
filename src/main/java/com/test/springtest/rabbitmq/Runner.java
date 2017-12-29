package com.test.springtest.rabbitmq;

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
public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final ConfigurableApplicationContext context;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate,
                  ConfigurableApplicationContext context) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        System.out.println("SendTime :" + new Date());
        String messageText = "";

        //第一条消息
        MessageProperties properties = new MessageProperties();
        properties.setDelay(6500);
        properties.setContentType(MediaType.APPLICATION_JSON_VALUE);
        properties.setHeader(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        messageText = "我是第一条消息:" + properties.getDelay();

        rabbitTemplate.send(SpringtestApplication.DELAY_EXCHANGE, SpringtestApplication.queueName, MessageBuilder.withBody(messageText.getBytes()).andProperties(properties).build());

        //第二条消息
        MessageProperties properties2 = new MessageProperties();
        properties2.setDelay(3500);
        properties2.setContentType(MediaType.APPLICATION_JSON_VALUE);
        properties2.setHeader(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        messageText = "我是第二条消息:" + properties2.getDelay();

        rabbitTemplate.send(SpringtestApplication.DELAY_EXCHANGE, SpringtestApplication.queueName, MessageBuilder.withBody(messageText.getBytes()).andProperties(properties2).build());


        //第三条消息
        MessageProperties properties3 = new MessageProperties();
        properties3.setDelay(10500);
        properties3.setContentType(MediaType.APPLICATION_JSON_VALUE);
        properties3.setHeader(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        messageText = "我是第三条消息:" + properties3.getDelay();

        rabbitTemplate.send(SpringtestApplication.DELAY_EXCHANGE, SpringtestApplication.queueName, MessageBuilder.withBody(messageText.getBytes()).andProperties(properties3).build());


    }
}
