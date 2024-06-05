package com.example.Hexagonal_architecture_pattern.service;

import com.example.Hexagonal_architecture_pattern.dto.PaymentEvent;
import com.example.Hexagonal_architecture_pattern.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    public void addPayment(PaymentEvent event) {
        paymentMapper.addPayment(event);
    }

    public void updatePayment(PaymentEvent event) {
        paymentMapper.updatePayment(event);
    }

    public void deletePayment(int paymentId) {
        paymentMapper.deletePayment(paymentId);
    }
}
