package com.hiwijaya.springrabbitmq.exchangeDirect;

import com.hiwijaya.springrabbitmq.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

import static com.hiwijaya.springrabbitmq.exchangeDirect.DirectExchangeConfig.*;


@RestController
public class DirectExchangePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/direct/order")
    public String bookOrder(@RequestBody Order order){

        order.setId(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE, ROUTING_KEY, order);

        return "success";
    }

}
