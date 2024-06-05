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

    @PostMapping("/orderCreated")
    public ResponseEntity<PaymentEvent> handleOrderCreated(@RequestBody PaymentEvent paymentEvent) {
        paymentEvent.setEventType("ORDER_CREATED");
        paymentSaga.handle(paymentEvent);
        return new ResponseEntity<>(paymentEvent, HttpStatus.CREATED);
    }

    @PostMapping("/paymentCompleted")
    public ResponseEntity<PaymentEvent> handlePaymentCompleted(@RequestBody PaymentEvent paymentEvent) {
        paymentEvent.setEventType("PAYMENT_COMPLETED");
        paymentSaga.handle(paymentEvent);
        return new ResponseEntity<>(paymentEvent, HttpStatus.CREATED);
    }

    @PostMapping("/paymentFailed")
    public ResponseEntity<PaymentEvent> handlePaymentFailed(@RequestBody PaymentEvent paymentEvent) {
        paymentEvent.setEventType("PAYMENT_FAILED");
        paymentSaga.handle(paymentEvent);
        return new ResponseEntity<>(paymentEvent, HttpStatus.CREATED);
    }
}
