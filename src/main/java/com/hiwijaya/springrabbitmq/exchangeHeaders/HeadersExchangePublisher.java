package com.hiwijaya.springrabbitmq.exchangeHeaders;

import com.hiwijaya.springrabbitmq.model.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
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

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("format", "pdf");
        messageProperties.setHeader("type", "report");

        MessageConverter messageConverter = new SimpleMessageConverter();
        Message message = messageConverter.toMessage(order, messageProperties);

        rabbitTemplate.convertAndSend(HEADERS_EXCHANGE, "", message);

        return "success";
    }

}
