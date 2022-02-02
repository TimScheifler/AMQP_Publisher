package com.timscheifler.rabbitmq;

import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }
}