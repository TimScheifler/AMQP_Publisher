package com.timscheifler.rabbitmq;

import java.sql.Timestamp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private static final int WAIT_TIME = 1000;

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        while(true){
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            byte[] data = SerializationUtils.serialize(ts);

            System.out.println("Sending message...");
            rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.baz", data);
            Thread.sleep(WAIT_TIME);
        }
    }
}