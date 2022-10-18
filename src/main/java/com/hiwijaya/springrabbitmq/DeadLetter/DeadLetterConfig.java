package com.hiwijaya.springrabbitmq.DeadLetter;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadLetterConfig {

    public static final String QUEUE_MESSAGES = "messages_queue";       // throw exception
    public static final String EXCHANGE_MESSAGES = "messages_exchange";

    public static final String QUEUE_PARKING_LOT = "parking_lot_queue";
    public static final String QUEUE_DEAD_LETTER = "dead_letter_queue";
    public static final String EXCHANGE_DEAD_LETTER = "dead_letter_exchange";
    public static final String EXCHANGE_PARKING_LOT = "parking_lot_exchange";

    public static final String HEADER_X_RETRIES_COUNT = "x-retries-count";


    @Bean
    public Declarables binding() {

        Queue queue = QueueBuilder.durable(QUEUE_MESSAGES)
                .withArgument("x-dead-letter-exchange", EXCHANGE_DEAD_LETTER)
                .build();
        DirectExchange exchange = ExchangeBuilder.directExchange(EXCHANGE_MESSAGES).build();

        Queue deadQueue = QueueBuilder.durable(QUEUE_DEAD_LETTER).build();
        FanoutExchange deadLetterExchange = ExchangeBuilder.fanoutExchange(EXCHANGE_DEAD_LETTER).build();

        Queue parkingQueue = QueueBuilder.durable(QUEUE_PARKING_LOT).build();
        FanoutExchange parkingExchange = ExchangeBuilder.fanoutExchange(EXCHANGE_PARKING_LOT).build();

        return new Declarables(
                queue, exchange,
                deadQueue, deadLetterExchange,
                parkingQueue, parkingExchange,
                BindingBuilder.bind(queue).to(exchange).with(QUEUE_MESSAGES),
                BindingBuilder.bind(deadQueue).to(deadLetterExchange),
                BindingBuilder.bind(parkingQueue).to(parkingExchange));
    }

}
