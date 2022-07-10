package com.hiwijaya.springrabbitmq.exchangeTopic;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {

    public static final String TOPIC_EXCHANGE = "order.topic";

    public static final String QUEUE_SEND_EMAIL = "order.send_email";

    public static final String QUEUE_GENERATE_PDF = "order.generate_pdf";

    public static final String QUEUE_LOGS = "order.logs";


    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Declarables topicBinding(TopicExchange exchange){

        Queue sendEmailQueue = new Queue(QUEUE_SEND_EMAIL);
        Queue generatePdfQueue = new Queue(QUEUE_GENERATE_PDF);
        Queue logsQueue = new Queue(QUEUE_LOGS);

        return new Declarables(exchange,
                sendEmailQueue,
                generatePdfQueue,
                logsQueue,
                BindingBuilder.bind(sendEmailQueue).to(exchange).with("order.created.*"),
                BindingBuilder.bind(generatePdfQueue).to(exchange).with("order.created.*"),
                BindingBuilder.bind(logsQueue).to(exchange).with("order.#")
        );


    }

}
