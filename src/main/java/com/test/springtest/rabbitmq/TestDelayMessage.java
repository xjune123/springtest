package com.test.springtest.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class TestDelayMessage {

    public static final String  EXCHANGE_NAME="my-delay-exchange";
    private final static String ROUTING_KEY="key_delay";


    @Autowired
    ConnectionFactory factory;
    public void delayMessage(){
        Connection connection = factory.createConnection();
        Channel channel = connection.createChannel(false);


        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        try {
            channel.exchangeDeclare(EXCHANGE_NAME, "x-delayed-message", true, false, args);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Map<String, Object> headers = new HashMap<String, Object>();
        Date now = new Date();
        Date timeToPublish = new Date("2017/12/27,17:00:12");

        String readyToPushContent = "publish at " + sf.format(now)
                + " \t deliver at " + sf.format(timeToPublish);

        headers.put("x-delay", timeToPublish.getTime() - now.getTime());

        AMQP.BasicProperties.Builder props = new AMQP.BasicProperties.Builder()
                .headers(headers);
        try {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, props.build(),
                    readyToPushContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 关闭频道和连接
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        connection.close();

    }
}
