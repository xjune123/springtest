- > 延时消息两种方式
1. -死信队列方式

---
RabbitMQ的Queue可以配置x-dead-letter-exchange 和x-dead-letter-routing-key（可选）两个参数，如果队列内出现了dead letter，则按照这两个参数重新路由。

x-dead-letter-exchange：出现dead letter之后将dead letter重新发送到指定exchange
x-dead-letter-routing-key：指定routing-key发送
队列出现dead letter的情况有：

消息或者队列的TTL过期

队列达到最大长度

消息被消费端拒绝（basic.reject or basic.nack）并且requeue=false

利用DLX，当消息在一个队列中变成死信后，它能被重新publish到另一个Exchange。这时候消息就可以重新被消费。

2. 使用rabbitmq-delayed-message-exchange插件
项目代码地址

```
https://github.com/xjune123/springtest.git
```

---

插件源码地址： 
https://github.com/rabbitmq/rabbitmq-delayed-message-exchange

插件下载地址： 
https://bintray.com/rabbitmq/community-plugins/rabbitmq_delayed_message_exchange

将下载的插件放到rabbit plugins目录下
启动插件
rabbitmq-plugins enable rabbitmq_delayed_message_exchange

关闭插件
rabbitmq-plugins disable rabbitmq_delayed_message_exchange


###### 代码如下：

一种是rabbitmq原生的实现方式，另外一种使用spring ampq封装，原理类似


```
        //第一条消息
        MessageProperties properties = new MessageProperties();
        properties.setDelay(6500);
        properties.setContentType(MediaType.APPLICATION_JSON_VALUE);
        properties.setHeader(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        messageText = "我是第一条消息:" + properties.getDelay();

        rabbitTemplate.send(SpringtestApplication.DELAY_EXCHANGE, SpringtestApplication.queueName, MessageBuilder.withBody(messageText.getBytes()).andProperties(properties).build());

```
主要属性
        properties.setDelay(6500);
        
声明延时消息队列

```
    @Bean
    TopicExchange exchange() {
        TopicExchange topicExchange = new TopicExchange("my-delay-exchange");
        topicExchange.setDelayed(true);
        return topicExchange;
    }

```
