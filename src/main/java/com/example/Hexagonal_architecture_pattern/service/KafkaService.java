package com.example.Hexagonal_architecture_pattern.service;

import com.google.gson.Gson;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.gson = new Gson();
    }

    public void sendMessageAsync(String topic, String payload) throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            kafkaTemplate.send(topic, payload);
        });
        future.get(1, TimeUnit.SECONDS); // Wait for 1 seconds
    }
}