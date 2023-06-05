package com.kafika.paymentservice.service;

import com.kafika.paymentservice.model.Payment;

public interface PaymentService {

    void sendPayment(Payment payment);
}
