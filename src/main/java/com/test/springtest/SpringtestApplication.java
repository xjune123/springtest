package com.test.springtest;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringtestApplication {
    public final static String queueName = "my-delay-queue";
    public final static String DELAY_EXCHANGE ="my-delay-exchange";
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    /*@Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "onMessage1");
    }*/

    @Bean
    TopicExchange exchange() {
		/*Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-delayed-type", "direct");*/
        TopicExchange topicExchange = new TopicExchange("my-delay-exchange");
        topicExchange.setDelayed(true);
        return topicExchange;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringtestApplication.class, args);
    }
}
