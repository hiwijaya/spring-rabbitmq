package com.hiwijaya.springrabbitmq.exchangeDirect;

import com.hiwijaya.springrabbitmq.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectExchangeConsumer {

    @RabbitListener(queues = DirectExchangeConfig.QUEUE_SEND_EMAIL)
    public void consumeSendEmailQueue(Order order){
        System.out.println("SEND_EMAIL CONSUMED");
        System.out.println(order);
    }

    @RabbitListener(queues = DirectExchangeConfig.QUEUE_GENERATE_PDF)
    public void consumeGeneratePdfQueue(Order order){
        System.out.println("GENERATE_PDF CONSUMED");
        System.out.println(order);
    }


}
