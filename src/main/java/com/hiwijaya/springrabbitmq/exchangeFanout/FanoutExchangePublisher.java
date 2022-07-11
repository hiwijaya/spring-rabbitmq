package com.hiwijaya.springrabbitmq.exchangeFanout;

import com.hiwijaya.springrabbitmq.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;
import static com.hiwijaya.springrabbitmq.exchangeFanout.FanoutExchangeConfig.*;


@RestController
public class FanoutExchangePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/fanout/order")
    public String bookOrder(@RequestBody Order order){

        order.setId(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE, "", order);

        return "success";
    }
}
