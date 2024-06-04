package com.example.Hexagonal_architecture_pattern.service;

import com.google.gson.Gson;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.gson = new Gson();
    }

    public void sendMessage(String topic, String payload) {
        kafkaTemplate.send(topic, payload);
    }
}