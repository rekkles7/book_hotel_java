package com.ctgu.bs_hotel.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName RabbitConfig
 * Description
 * Create by luochuang
 * Date 2022/4/11 2:00 下午
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue queue() {
        Map<String, Object> props = new HashMap<>();
        // 消息的生存时间 10s
        props.put("x-message-ttl", 900000);
        // 设置该队列所关联的死信交换器（当队列消息到期后依然没有消费，则加入死信 队列）
        props.put("x-dead-letter-exchange", "ex.order.dlx");
        // 设置该队列所关联的死信交换器的routingKey，如果没有特殊指定，使用原队列的routingKey
        props.put("x-dead-letter-routing-key", "order.dlx");
        Queue queue = new Queue("q.order", true, false, false, props);
        return queue;
    }
    @Bean
    public Queue queueDlx() {
        Queue queue = new Queue("q.order.dlx", true, false, false);
        return queue;
    }
    @Bean
    public Exchange exchange() {
        DirectExchange exchange = new DirectExchange("ex.order", true,false, null);
        return exchange;
    }
    /**
     * 死信交换器
     * @return
     */
    @Bean
    public Exchange exchangeDlx() {
        DirectExchange exchange = new DirectExchange("ex.order.dlx", true,false, null);
        return exchange;
    }
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("order").noargs();
    }

    @Bean
    public Binding bindingDlx() {
        return BindingBuilder.bind(queueDlx()).to(exchangeDlx()).with("order.dlx").noargs();
    }
}

