package com.test.springtest.dlxmessage;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * Demo class
 *
 * @author junqiang.xiao
 * @date 2019/3/29 下午5:40
 */
@Slf4j
@Component
public class ReceiveDelayMessage {
    @RabbitListener(queues = {"REDIRECT_QUEUE"})
    public void redirect(Message message, Channel channel) throws IOException {
        System.out.println(new Date()+" messagebody"+new String (message.getBody()));
        log.debug("dead message 时间 {} 消费消息 {}",new Date(),new String (message.getBody()));
    }
}
