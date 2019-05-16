package com.test.springtest;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan(value = "com.test.springtest.transaction.mapper")
public class SpringtestApplication {
    public final static String QUEUE_NAME = "my-delay-queue";
    public final static String DELAY_EXCHANGE = "my-delay-exchange";

    public final static String PRIORITY_QUEUE_NAME = "my-priority-queue";
    public final static String PRIORITY_EXCHANGE = "my-priority-exchange";

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME);
    }

    /*
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "onMessage");
    }
*/
    @Bean
    TopicExchange exchange() {
		/*Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-delayed-type", "direct");*/
        TopicExchange topicExchange = new TopicExchange("my-delay-exchange");
        topicExchange.setDelayed(true);
        return topicExchange;
    }


    @Bean
    Queue queuePriority() {
        //Dictionary<String, Object> dic = new Dictionary<String, Object>();
        //dic.put("x-max-priority", 20);

        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-max-priority", 20);
        Queue queue = new Queue(PRIORITY_QUEUE_NAME, true, false, false, args);

        return queue;
    }

    @Bean
    Binding bindingPriority(Queue queuePriority, TopicExchange exchangePriority) {
        return BindingBuilder.bind(queuePriority).to(exchangePriority).with(PRIORITY_QUEUE_NAME);
    }

    @Bean
    TopicExchange exchangePriority() {
		/*Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-delayed-type", "direct");*/
        TopicExchange topicExchange = new TopicExchange(PRIORITY_EXCHANGE);
        return topicExchange;
    }




    public static void main(String[] args) {
        SpringApplication.run(SpringtestApplication.class, args);
    }
}
