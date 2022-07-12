package com.hiwijaya.springrabbitmq.delayedMessage;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelayedMessageConfig {

    public static final String DELAYED_EXCHANGE = "order.delayed";

    public static final String ROUTING_KEY = "order.created";

    public static final String QUEUE_EXPIRY = "order.expiry";


    @Bean
    public Queue queue(){
        return new Queue(QUEUE_EXPIRY);
    }

    @Bean()
    public DirectExchange delayedExchange(){
        return ExchangeBuilder.directExchange(DELAYED_EXCHANGE).delayed().build();
    }

    @Bean
    public Binding bindingDelayed(DirectExchange exchange, Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }



}
