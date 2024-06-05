package com.example.Hexagonal_architecture_pattern.controller;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * 애플리케이션 실행 시 아래의 클래스가 실행 되며,
 * 아래의 클래스는 카프카에서 입력되는 데이터를 가져와 Json Tytpe으로 출력하는 역할을 한다.
 */

@Component
public class KafkaDataRetriever {

    private static KafkaConsumer<String, String> getStringStringKafkaConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "zzanggu-test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // Create the Kafka consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        return consumer;
    }

    @PostConstruct
    public void init() {
        new Thread(this::kafkaDataRetriever).start();
    }

    public void kafkaDataRetriever() {
        // Set up the properties for the Kafka consumer
        KafkaConsumer<String, String> consumer = getStringStringKafkaConsumer();

        // Subscribe to the "exam" topic
        consumer.subscribe(Collections.singletonList("exam"));

        // Continuously read from the topic
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                // Print out the data
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }
}
