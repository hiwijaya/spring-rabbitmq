package com.hiwijaya.springrabbitmq.exchangeDirect;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

    public static final String DIRECT_EXCHANGE = "order.direct";

    public static final String ROUTING_KEY = "order.created";

    public static final String QUEUE_SEND_EMAIL = "order.send_email";

    public static final String QUEUE_GENERATE_PDF = "order.generate_pdf";

//    @Bean(name = "sendEmailQueue")
//    public Queue sendEmailQueue(){
//        return new Queue(QUEUE_SEND_EMAIL);
//    }
//
//    @Bean(name = "generatePdfQueue")
//    public Queue generatePdfQueue(){
//        return new Queue(QUEUE_GENERATE_PDF);
//    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Declarables directBinding(DirectExchange exchange){

        Queue sendEmailQueue = new Queue(QUEUE_SEND_EMAIL);
        Queue generatePdfQueue = new Queue(QUEUE_GENERATE_PDF);

        return new Declarables(sendEmailQueue, generatePdfQueue, exchange,
                BindingBuilder.bind(sendEmailQueue).to(exchange).with(ROUTING_KEY),
                BindingBuilder.bind(generatePdfQueue).to(exchange).with(ROUTING_KEY)
        );
    }




}

