package com.test.springtest;

import com.test.springtest.rabbitmq.Receiver;
import com.test.springtest.rabbitmq.Runner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringtestApplicationTests {

	@MockBean
	private Runner runner;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Receiver receiver;

	@Test
	public void test() throws Exception {
		/*rabbitTemplate.convertAndSend(SpringtestApplication.queueName, "Hello from RabbitMQ!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);*/
		runner.run("Hello delay message!");
	}

}