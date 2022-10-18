package com.hiwijaya.springrabbitmq.DeadLetter;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadLetterConfig {

    public static final String QUEUE_MESSAGES = "messages_queue";       // throw exception
    public static final String EXCHANGE_MESSAGES = "messages_exchange";

    public static final String QUEUE_DEAD_LETTER = "dead_letter_queue";
    public static final String EXCHANGE_DEAD_LETTER = "dead_letter_exchange";


    @Bean
    public Declarables binding() {

        Queue queue = QueueBuilder.durable(QUEUE_MESSAGES)
                .withArgument("x-dead-letter-exchange", EXCHANGE_DEAD_LETTER)
                .build();
        DirectExchange exchange = ExchangeBuilder.directExchange(EXCHANGE_MESSAGES).build();

        Queue deadQueue = QueueBuilder.durable(QUEUE_DEAD_LETTER).build();
        FanoutExchange deadLetterExchange = ExchangeBuilder.fanoutExchange(EXCHANGE_DEAD_LETTER).build();

        return new Declarables(
                queue, exchange,
                deadQueue, deadLetterExchange,
                BindingBuilder.bind(queue).to(exchange).with(QUEUE_MESSAGES),
                BindingBuilder.bind(deadQueue).to(deadLetterExchange));
    }

}
