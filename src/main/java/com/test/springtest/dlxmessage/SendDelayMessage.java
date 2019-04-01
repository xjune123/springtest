package com.test.springtest.dlxmessage;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 发送延时消息
 *
 * @author junqiang.xiao
 * @date 2019/3/29 下午5:05
 */
@Service
public class SendDelayMessage {
    public static final String EXCHANGE_NAME = "DL_EXCHANGE";
    public static final  String ROUTING_KEY = "DL_KEY";
    @Autowired
    ConnectionFactory factory;

    public void send() throws IOException {
        Connection connection = factory.createConnection();
        Channel channel = connection.createChannel(false);

        byte[] messageBodyBytes = "Hello, world1!".getBytes();
        AMQP.BasicProperties properties = new AMQP.BasicProperties();
        properties.builder().expiration("10000");
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, properties, messageBodyBytes);

        //
       messageBodyBytes = "Hello, world2!".getBytes();
        properties.builder().expiration("15000");
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, properties, messageBodyBytes);


        messageBodyBytes = "Hello, world3!".getBytes();
        properties.builder().expiration("25000");
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, properties, messageBodyBytes);
    }
}
