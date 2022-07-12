package com.hiwijaya.springrabbitmq.delayedMessage;

import static com.hiwijaya.springrabbitmq.delayedMessage.DelayedMessageConfig.*;
import com.hiwijaya.springrabbitmq.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DelayedMessageConsumer {

    @RabbitListener(queues = QUEUE_EXPIRY)
    public void consumeExpiryQueue(Order order){
        System.out.println("EXPIRY CONSUMED");
        System.out.println(order);
    }

}
