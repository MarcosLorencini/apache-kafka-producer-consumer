package com.kafika.strconsumer.strconsumer.listeners;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class StrConsumerListener {

    //defini o nome do group Id
    //escuta do topico de nome str-topic
    //utiliza o strContainerFactory(definido no arquivo de config) para fazer a leitura do topico
    @KafkaListener(groupId = "group-1", topics = "str-topic", containerFactory = "strContainerFactory")
    public void listener(String message) {
        log.info("Receive message {}", message);
    }
}
