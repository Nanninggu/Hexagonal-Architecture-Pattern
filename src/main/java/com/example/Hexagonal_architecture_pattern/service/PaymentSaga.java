package com.example.Hexagonal_architecture_pattern.service;

import com.example.Hexagonal_architecture_pattern.dto.PaymentEvent;
import com.example.Hexagonal_architecture_pattern.mapper.PaymentMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class PaymentSaga {
    private final PaymentMapper paymentMapper;
    private final KafkaService kafkaService;

    public PaymentSaga(PaymentMapper paymentMapper, KafkaService kafkaService) {
        this.paymentMapper = paymentMapper;
        this.kafkaService = kafkaService;
    }

    @Transactional
    public void handle(PaymentEvent event) {
        try {
            switch (event.getEventType()) {
                case "ORDER_CREATED":
                    // Convert event to JSON string
                    String payload = new ObjectMapper().writeValueAsString(event);
                    event.setPayload(payload);

                    // Add payment
                    paymentMapper.addPayment(event);

                    // Send message to Kafka
                    try {
                        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                            kafkaService.sendMessage("exam", payload);
                        });
                        future.get(1, TimeUnit.SECONDS); // Wait for 10 seconds
                    } catch (InterruptedException | ExecutionException | TimeoutException e) {
                        // Handle the exception
                        System.out.println("Connection to Kafka failed or timed out.");
                        return; // Stop the connection attempt if the connection fails
                    }
                    break;

                case "PAYMENT_COMPLETED":
                    // Get paymentId from event
                    int paymentId = event.getPaymentId();
                    event.setPaymentId(paymentId); // Set paymentId on event

                    // Update payment with event containing paymentId
                    paymentMapper.updatePayment(event);
                    break;

                case "PAYMENT_FAILED":
                    // Handle payment failure
                    paymentMapper.deletePayment(event.getPaymentId());
                    break;

                default:
                    throw new IllegalArgumentException("Invalid event type");
            }
        } catch (Exception e) {
            // Handle exception
        }
    }
}
