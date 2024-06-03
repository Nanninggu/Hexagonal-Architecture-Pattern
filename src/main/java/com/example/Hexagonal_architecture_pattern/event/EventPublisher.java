package com.example.Hexagonal_architecture_pattern.event;

import com.example.Hexagonal_architecture_pattern.dto.PaymentEvent;

public interface EventPublisher {
    void publish(PaymentEvent event);
}
