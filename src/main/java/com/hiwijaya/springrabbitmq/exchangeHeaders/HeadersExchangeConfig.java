package com.hiwijaya.springrabbitmq.exchangeHeaders;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class HeadersExchangeConfig {

    public static final String HEADERS_EXCHANGE = "agreements";

    public static final String QUEUE_A = "queue.a";

    public static final String QUEUE_B = "queue.b";

    public static final String QUEUE_C = "queue.c";


    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange(HEADERS_EXCHANGE);
    }

    @Bean
    public Declarables headersBinding(HeadersExchange exchange){

        Queue queueA = new Queue(QUEUE_A);
        Queue queueB = new Queue(QUEUE_B);
        Queue queueC = new Queue(QUEUE_C);

        return new Declarables(exchange,
                queueA,
                queueB,
                queueC,
                BindingBuilder.bind(queueA).to(exchange)
                        .whereAll(Map.of("format", "pdf", "type", "report")).match(),
                BindingBuilder.bind(queueB).to(exchange)
                        .whereAny(Map.of("format", "pdf", "type", "log")).match(),
                BindingBuilder.bind(queueC).to(exchange)
                        .whereAll(Map.of("format", "zip", "type", "report")).match()
        );

    }
    


}
