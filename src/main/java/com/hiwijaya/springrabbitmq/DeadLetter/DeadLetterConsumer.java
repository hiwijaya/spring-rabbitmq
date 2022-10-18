package com.hiwijaya.springrabbitmq.DeadLetter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeadLetterConsumer {

    @RabbitListener(queues = DeadLetterConfig.QUEUE_MESSAGES)
    public void consumeMessageA(String message) throws OnPurposeException {

        log.info("Consuming message...");

        throw new OnPurposeException();
    }


    @RabbitListener(queues = DeadLetterConfig.QUEUE_DEAD_LETTER)
    public void consumeDeadLetter(String message){

        log.warn("Consumed dead letter: {}", message);

    }


}
