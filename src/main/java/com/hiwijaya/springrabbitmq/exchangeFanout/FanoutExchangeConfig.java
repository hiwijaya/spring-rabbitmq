package com.hiwijaya.springrabbitmq.exchangeFanout;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutExchangeConfig {

    public static final String FANOUT_EXCHANGE = "order.fanout";

    public static final String QUEUE_SEND_EMAIL = "order.send_email";

    public static final String QUEUE_GENERATE_PDF = "order.generate_pdf";

    public static final String QUEUE_LOGS = "order.logs";


    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Declarables fanoutBinding(FanoutExchange exchange){

        Queue sendEmailQueue = new Queue(QUEUE_SEND_EMAIL);
        Queue generatePdfQueue = new Queue(QUEUE_GENERATE_PDF);
        Queue logsQueue = new Queue(QUEUE_LOGS);

        return new Declarables(exchange,
                sendEmailQueue,
                generatePdfQueue,
                logsQueue,
                BindingBuilder.bind(sendEmailQueue).to(exchange),
                BindingBuilder.bind(generatePdfQueue).to(exchange),
                BindingBuilder.bind(logsQueue).to(exchange)
        );


    }





}
