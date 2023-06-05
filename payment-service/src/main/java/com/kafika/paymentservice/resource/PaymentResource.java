package com.kafika.paymentservice.resource;

import com.kafika.paymentservice.model.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentResource {
    //O certo é receber um dto
    @PostMapping
    ResponseEntity<Payment> payment(@RequestBody Payment payment);
}
