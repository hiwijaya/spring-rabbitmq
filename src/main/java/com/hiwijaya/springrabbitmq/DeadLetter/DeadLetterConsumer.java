package com.hiwijaya.springrabbitmq.DeadLetter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeadLetterConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static final int MAX_RETRIES_COUNT = 3;


    @RabbitListener(queues = DeadLetterConfig.QUEUE_MESSAGES)
    public void consumeMessageA(String message) throws OnPurposeException {

        log.info("Consuming message...");

        throw new OnPurposeException();
    }

    @RabbitListener(queues = DeadLetterConfig.QUEUE_DEAD_LETTER)
    public void consumeDeadLetter(Message failedMessage){

        Integer retriesCount = (Integer) failedMessage.getMessageProperties().getHeaders()
                .get(DeadLetterConfig.HEADER_X_RETRIES_COUNT);

        retriesCount = (retriesCount == null) ? 1 : retriesCount;

        if(retriesCount > MAX_RETRIES_COUNT){
            log.warn("Sending message to parking lot queue");
            rabbitTemplate.send(
                    DeadLetterConfig.EXCHANGE_PARKING_LOT,
                    failedMessage.getMessageProperties().getReceivedRoutingKey(),
                    failedMessage);
            return;
        }

        log.info("Retrying message for the {} time", retriesCount);

        failedMessage.getMessageProperties().getHeaders()
                .put(DeadLetterConfig.HEADER_X_RETRIES_COUNT, ++ retriesCount);
        rabbitTemplate.send(
                DeadLetterConfig.EXCHANGE_MESSAGES,
                failedMessage.getMessageProperties().getReceivedRoutingKey(),
                failedMessage);

    }

    @RabbitListener(queues = DeadLetterConfig.QUEUE_PARKING_LOT)
    public void parkingLot(Message failedMessage){

        log.warn("Received message in parking lot queue");

        // Save to DB or send a notification

    }


}
