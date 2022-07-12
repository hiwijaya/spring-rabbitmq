package com.hiwijaya.springrabbitmq.delayedMessage;

import com.hiwijaya.springrabbitmq.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.hiwijaya.springrabbitmq.delayedMessage.DelayedMessageConfig.*;

@RestController
public class DelayedMessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/delayed/order")
    public String bookOrder(@RequestBody Order order){

        order.setId(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(DELAYED_EXCHANGE, ROUTING_KEY, order, message -> {
            message.getMessageProperties().setDelay(10 * 1_000);     // in milliseconds
            return message;
        });

        return "success";
    }

}
