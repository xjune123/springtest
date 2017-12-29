package com.test.springtest.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TestReceiveDelayMessage {
    private final static String QUEUE_NAME = "delay_queue";
    private final static String EXCHANGE_NAME= TestDelayMessage.EXCHANGE_NAME;

    @Autowired
    ConnectionFactory factory;
    public void getDelayMessage(){
        Connection connection = factory.createConnection();
        Channel channel = connection.createChannel(false);
    }
}
