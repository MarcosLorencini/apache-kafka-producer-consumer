package com.kafika.strconsumer.strconsumer.listeners;

import com.kafika.strconsumer.strconsumer.custom.StrConsumerCustomListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class StrConsumerListener {

    //definir o nome do groupId que este consumer pertence, no caso ele percente ao groupId = group1
    //escuta do topico de nome str-topic
    //utiliza o strContainerFactory(definido no arquivo de config) para fazer a leitura do topico

    //Observacao: no minimo a quantidade de consumer deve ser equivalente a quantidade de partiçoes
    //caso eles estejam cadastrado no mesmo groupId

    //Pode ter groupId diferentes olhando para o mesmo topico(em paralelo)

    @StrConsumerCustomListener(groupId = "group-1")
    public void create(String message) {
        log.info("CREATE ::: Receive message {}", message);
    }

    @StrConsumerCustomListener(groupId = "group-1")
    public void log(String message) {
        log.info("LOG ::: Receive message {}", message);
    }

    @StrConsumerCustomListener(groupId = "group-2")
    public void history(String message) {
        log.info("HISTORY ::: Receive message {}", message);
    }

    /*
    //definido uma partição "0" para este metodo do mesmo grupo
    @KafkaListener(groupId = "group-1",
            topicPartitions = {
                    @TopicPartition(topic = "str-topic", partitions = {"0"}) //array de partições
            },
            containerFactory = "strContainerFactory")
    public void create(String message) {
        log.info("CREATE ::: Receive message {}", message);
    }

    //definido uma partição "1" para este metodo do mesmo grupo
    @KafkaListener(groupId = "group-1",
            topicPartitions = {
                @TopicPartition(topic = "str-topic", partitions = {"1"}) //array de partições
            },
            containerFactory = "strContainerFactory")
    public void log(String message) {
        log.info("LOG ::: Receive message {}", message);
    }


    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "strContainerFactory")
    public void history(String message) {
        log.info("HISTORY ::: Receive message {}", message);
    }*/


}
