package com.example;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener("demo")
public class DemoConsumer {

    @Topic("demo")
    void receive(@KafkaKey String id, String value) {
        System.out.println("id: " + id + ", value: " + value);
    }
}
