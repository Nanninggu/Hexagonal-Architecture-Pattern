package com.example.Hexagonal_architecture_pattern.service;

import com.example.Hexagonal_architecture_pattern.dto.PaymentEvent;
import com.example.Hexagonal_architecture_pattern.event.EventPublisher;
import com.example.Hexagonal_architecture_pattern.mapper.PaymentMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentSaga {
    private final PaymentMapper paymentMapper;
    private final EventPublisher eventPublisher;
    private final KafkaService kafkaService;

    public PaymentSaga(PaymentMapper paymentMapper, EventPublisher eventPublisher, KafkaService kafkaService) {
        this.paymentMapper = paymentMapper;
        this.eventPublisher = eventPublisher;
        this.kafkaService = kafkaService;
    }

    @Transactional
    public void handle(PaymentEvent event) {
        try {
            switch (event.getEventType()) {
                case "ORDER_CREATED":
                    String payload = new ObjectMapper().writeValueAsString(event); // convert event to JSON string
                    event.setPayload(payload);
                    // handle order creation
                    paymentMapper.addPayment(event);
                    if ("ORDER_CREATED".equals(event.getEventType())) {
                        kafkaService.sendMessage("exam", payload);
                    }
                    break;
                case "PAYMENT_COMPLETED":
                    // handle payment completion
                    paymentMapper.updatePayment(event);
                    break;
                case "PAYMENT_FAILED":
                    // handle payment failure
                    paymentMapper.deletePayment(event.getPaymentId());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid event type");
            }
        } catch (Exception e) {
            // handle exception
        }
    }
}
