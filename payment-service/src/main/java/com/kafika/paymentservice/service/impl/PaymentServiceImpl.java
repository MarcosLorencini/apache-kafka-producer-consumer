package com.kafika.paymentservice.service.impl;

import com.kafika.paymentservice.model.Payment;
import com.kafika.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@RequiredArgsConstructor
@Log4j2
@Service
public class PaymentServiceImpl implements PaymentService {

    //injeta o KafkaTemplate criado na class JsonProducerConfig
    //com base no bean criado na classe acima o spring injeta o KafakTemplate
    private final KafkaTemplate<String, Serializable> kafkaTemplate;


    @SneakyThrows
    @Override
    public void sendPayment(Payment payment) {
        log.info("Recebi o pagamento {}", payment);

        //aguardar um segundo
        Thread.sleep(1000);

        log.info("Eviando pagamento...");

        kafkaTemplate.send("payment-topic", payment);
    }
}
