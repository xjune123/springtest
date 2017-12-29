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
import java.util.concurrent.TimeUnit;

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

        MessageProperties properties = new MessageProperties();
        properties.setDelay(150000);
        properties.setExpiration(String.valueOf(10000));
        properties.setContentType(MediaType.APPLICATION_JSON_VALUE);
        properties.setHeader(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        /*template.send(exchange, routingKey,
                MessageBuilder.withBody("foo".getBytes()).andProperties(properties).build());*/


       /* Message message = new Message("Hello delay message".getBytes(), MessagePropertiesBuilder
                .newInstance()
                //.setContentType(MediaType.APPLICATION_JSON_VALUE)
                .setDeliveryModeIfAbsentOrDefault(MessageDeliveryMode.PERSISTENT)
                .setPriority(0)
               // .setHeader(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                //.setExpiration(String.valueOf(1000))
                .build());*/

        //rabbitTemplate.convertAndSend(SpringtestApplication.queueName, message);

        //rabbitTemplate.convertAndSend(SpringtestApplication.queueName, "Hello from RabbitMQ!");
        rabbitTemplate.send(SpringtestApplication.DELAY_EXCHANGE, SpringtestApplication.queueName, MessageBuilder.withBody("Hello".getBytes()).andProperties(properties).build());
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        context.close();
    }
}
