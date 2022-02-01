package com.timscheifler.rabbitmq;

import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(byte[] data) {
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        TimeObject timeObject = (TimeObject) SerializationUtils.deserialize(data);

        System.out.println("Received <" + timeObject.toString() + ">");
        System.out.println("Time difference in millis: " + (time - timeObject.getTime()));
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}