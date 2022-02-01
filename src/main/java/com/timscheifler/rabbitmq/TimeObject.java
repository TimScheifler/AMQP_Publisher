package com.timscheifler.rabbitmq;

import java.io.Serializable;

public class TimeObject implements Serializable {

    private final long time;

    public TimeObject(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "TimeObject{" +
                "time=" + time +
                '}';
    }
}
