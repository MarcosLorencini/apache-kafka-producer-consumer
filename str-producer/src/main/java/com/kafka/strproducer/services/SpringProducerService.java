package com.kafka.strproducer.services;

//Service que vai enviar msg para o topico

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class SpringProducerService {

    //Já definido como bean na config
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info("Send message {}", message);
        kafkaTemplate.send("str-topic", message);
               /* .addCallback(
                success -> {
                    if(success != null) {
                        log.info("Partition {}, Offset {}",
                                success.getRecordMetadata().partition(),
                                success.getRecordMetadata().offset());
                    }
                },
                error -> log.error("Error send message")
        );*/
    }


}
