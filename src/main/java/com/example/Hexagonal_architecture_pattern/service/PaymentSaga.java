package com.example.Hexagonal_architecture_pattern.service;

import com.example.Hexagonal_architecture_pattern.dto.PaymentEvent;
import com.example.Hexagonal_architecture_pattern.event.EventPublisher;
import com.example.Hexagonal_architecture_pattern.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentSaga {

    @Autowired PaymentMapper paymentMapper;

    private final EventPublisher eventPublisher;

    public PaymentSaga(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void handle(PaymentEvent event) {
        switch (event.getEventType()) {
            case "ORDER_CREATED":
                // handle order creation
                paymentMapper.addPayment(event);
                break;
            case "PAYMENT_COMPLETED":
                // handle payment completion
//                eventPublisher.publish(event);
                break;
            case "PAYMENT_FAILED":
                // handle payment failure
//                eventPublisher.publish(event);
                break;
            default:
                throw new IllegalArgumentException("Invalid event type");
        }
    }
}
