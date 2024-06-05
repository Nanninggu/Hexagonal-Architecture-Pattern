package com.example.Hexagonal_architecture_pattern.service;

import com.example.Hexagonal_architecture_pattern.dto.PaymentEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Service
public class PaymentSaga {
    private final PaymentService paymentService;
    private final KafkaService kafkaService;

    public PaymentSaga(PaymentService paymentService, KafkaService kafkaService) {
        this.paymentService = paymentService;
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
                    paymentService.addPayment(event);

                    // Send message to Kafka
                    try {
                        kafkaService.sendMessageAsync("exam", payload);
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
                    paymentService.updatePayment(event);
                    break;

                case "PAYMENT_FAILED":
                    // Handle payment failure
                    paymentService.deletePayment(event.getPaymentId());
                    System.out.println("Payment failed for payment ID: " + event.getPaymentId());
                    break;

                default:
                    throw new IllegalArgumentException("Invalid event type");
            }
        } catch (Exception e) {
            // Handle exception
            System.out.println("An error occurred while processing the event: " + e.getMessage());
        }
    }
}
