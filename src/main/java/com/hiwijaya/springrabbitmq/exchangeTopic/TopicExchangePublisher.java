package com.hiwijaya.springrabbitmq.exchangeTopic;

import com.hiwijaya.springrabbitmq.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.hiwijaya.springrabbitmq.exchangeTopic.TopicExchangeConfig.*;

@RestController
public class TopicExchangePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/topic/order")
    public String bookOrder(@RequestBody Order order){

        order.setId(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, "order.created", order);

        return "success";
    }

}
