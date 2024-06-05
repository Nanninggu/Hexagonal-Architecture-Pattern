package com.example.Hexagonal_architecture_pattern.controller;

import com.example.Hexagonal_architecture_pattern.dto.PaymentEvent;
import com.example.Hexagonal_architecture_pattern.service.PaymentSaga;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 가상 결제 구현을 위한 컨트롤러
 * 결제 이벤트를 처리하는 PaymentSaga를 주입받아 결제 이벤트를 처리한다.
 */

@RestController
@RequestMapping("/client/payments")
public class ClientController {
    private final PaymentSaga paymentSaga;

    public ClientController(PaymentSaga paymentSaga) {
        this.paymentSaga = paymentSaga;
    }

    @PostMapping
    public ResponseEntity<PaymentEvent> handlePayment(@RequestBody PaymentEvent paymentEvent) {
        paymentSaga.handle(paymentEvent);
        return new ResponseEntity<>(paymentEvent, HttpStatus.CREATED);
    }
}
