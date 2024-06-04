package com.example.Hexagonal_architecture_pattern.event;

import com.example.Hexagonal_architecture_pattern.dto.PaymentEvent;
import com.google.gson.Gson;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
public class EventPublisherImpl implements EventPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    public EventPublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.gson = new Gson();
    }

    @Override
    public void publish(String payload) {
//        String message = gson.toJson(payload);
        kafkaTemplate.send("exam", payload);
    }
}
