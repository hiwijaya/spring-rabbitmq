package com.hiwijaya.springrabbitmq.exchangeHeaders;

import static com.hiwijaya.springrabbitmq.exchangeHeaders.HeadersExchangeConfig.*;
import com.hiwijaya.springrabbitmq.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeadersExchangeConsumer {

    @RabbitListener(queues = QUEUE_A)
    public void consumeQueueA(Order order){
        System.out.println("QUEUE A CONSUMED");
        System.out.println(order);
    }

    @RabbitListener(queues = QUEUE_B)
    public void consumeQueueB(Order order){
        System.out.println("QUEUE B CONSUMED");
        System.out.println(order);
    }

    @RabbitListener(queues = QUEUE_C)
    public void consumeQueueC(Order order){

        System.out.println("QUEUE C CONSUMED");
        System.out.println(order);

    }

}
