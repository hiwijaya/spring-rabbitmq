package com.hiwijaya.springrabbitmq.exchangeDirect;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

    public static final String DIRECT_EXCHANGE = "order.direct";

    public static final String ROUTING_KEY = "order.created";

    public static final String QUEUE_SEND_EMAIL = "order.send_email";

    public static final String QUEUE_GENERATE_PDF = "order.generate_pdf";


    @Bean
    public Declarables directBinding(){

        Queue sendEmailQueue = new Queue(QUEUE_SEND_EMAIL);
        Queue generatePdfQueue = new Queue(QUEUE_GENERATE_PDF);
        DirectExchange directExchange = new DirectExchange(DIRECT_EXCHANGE);

        return new Declarables(sendEmailQueue, generatePdfQueue, directExchange,
                BindingBuilder.bind(sendEmailQueue).to(directExchange).with(ROUTING_KEY),
                BindingBuilder.bind(generatePdfQueue).to(directExchange).with(ROUTING_KEY)
        );
    }




}

