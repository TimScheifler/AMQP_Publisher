package com.timscheifler.rabbitmq;

import java.sql.Timestamp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private static final int WAIT_TIME = 600;

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i = 1; i<=900;i++){
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            byte[] data = SerializationUtils.serialize(ts);

            System.out.println("Sending message " + i + "...");
            rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.baz", data);
            Thread.sleep(WAIT_TIME);
        }
    }
}