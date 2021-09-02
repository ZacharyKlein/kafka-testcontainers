package com.example;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient("demo")
public interface DemoProducer {

    @Topic("demo")
    void send(@KafkaKey String id, String value);
}
