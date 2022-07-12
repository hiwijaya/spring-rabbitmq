package com.hiwijaya.springrabbitmq.exchangeHeaders;

import com.hiwijaya.springrabbitmq.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.hiwijaya.springrabbitmq.exchangeHeaders.HeadersExchangeConfig.*;

@RestController
public class HeadersExchangePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/headers/order")
    public String bookOrder(@RequestBody Order order){

        order.setId(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(HEADERS_EXCHANGE, "", order, message -> {

            message.getMessageProperties().setHeader("format", "pdf");
            message.getMessageProperties().setHeader("type", "report");

            return message;
        });

        return "success";
    }

}
