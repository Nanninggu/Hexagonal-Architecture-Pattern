package com.example.Hexagonal_architecture_pattern.message;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageBrokerImpl implements MessageBroker {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "exam";

    public MessageBrokerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
