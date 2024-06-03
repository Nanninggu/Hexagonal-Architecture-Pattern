package com.example.Hexagonal_architecture_pattern.controller;

import com.example.Hexagonal_architecture_pattern.dto.PaymentEvent;
import com.example.Hexagonal_architecture_pattern.service.PaymentSaga;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentSaga paymentSaga;

    public PaymentController(PaymentSaga paymentSaga) {
        this.paymentSaga = paymentSaga;
    }

    @PostMapping
    public ResponseEntity<PaymentEvent> handlePayment(@RequestBody PaymentEvent paymentEvent) {
        paymentSaga.handle(paymentEvent);
        return new ResponseEntity<>(paymentEvent, HttpStatus.CREATED);
    }
}
