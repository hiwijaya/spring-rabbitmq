package com.hiwijaya.springrabbitmq.exchangeTopic;

import com.hiwijaya.springrabbitmq.exchangeDirect.DirectExchangeConfig;
import com.hiwijaya.springrabbitmq.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicExchangeConsumer {

    @RabbitListener(queues = TopicExchangeConfig.QUEUE_SEND_EMAIL)
    public void consumeSendEmailQueue(Order order){
        System.out.println("SEND_EMAIL CONSUMED");
        System.out.println(order);
    }

    @RabbitListener(queues = TopicExchangeConfig.QUEUE_GENERATE_PDF)
    public void consumeGeneratePdfQueue(Order order){
        System.out.println("GENERATE_PDF CONSUMED");
        System.out.println(order);
    }

    @RabbitListener(queues =  TopicExchangeConfig.QUEUE_LOGS)
    public void consumeLogsQueue(Order order){

        System.out.println("LOGS CONSUMED");
        System.out.println(order);

    }
}
