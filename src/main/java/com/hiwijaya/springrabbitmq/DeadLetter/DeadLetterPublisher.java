package com.hiwijaya.springrabbitmq.DeadLetter;

import com.hiwijaya.springrabbitmq.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.hiwijaya.springrabbitmq.delayedMessage.DelayedMessageConfig.DELAYED_EXCHANGE;
import static com.hiwijaya.springrabbitmq.delayedMessage.DelayedMessageConfig.ROUTING_KEY;

@Slf4j
@RestController
public class DeadLetterPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/dlx/send")
    public String sendMessageA(){

        log.info("Sending message...");

        rabbitTemplate.convertAndSend(
                DeadLetterConfig.EXCHANGE_MESSAGES,
                DeadLetterConfig.QUEUE_MESSAGES,
                "Message from /dlx");

        return "ok";
    }


}
