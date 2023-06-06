package com.kafika.paymentservice.config;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.io.Serializable;
import java.util.HashMap;

@RequiredArgsConstructor
@Configuration
public class JsonProducerConfig {

    private final KafkaProperties properties;

    @Bean
    public ProducerFactory jsonProducerFactory() {
        var configs = new HashMap<String, Object>();
        //pega localhost:29092 do application.yml
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        //define o objeto que vai fazer a serialização da nossa chave.Temos chave e valor na msg, precisa
        //ter um serializador para esta chave: StringSerializer.class
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //definir o serializar do valor da msg
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerialize.class);
        //objetos do tipo string passavamos apenas o "configs", agora tem que passar tbm um
        //keySerializer e um valueSerializer
        return new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), new JsonSerializer<>());
    }

    //Agora definir o kafka template para usar o metodo jsonProducerFactory() para poder eviar msg para os topicos
    @Bean
    public KafkaTemplate<String, Serializable> jsonKafkaTemplate(ProducerFactory producerFactory) {
        return new KafkaTemplate<>(producerFactory);//como existe a anotação @Bean no metodo producerFactory
        //acima que vai ficar reponsável por injetar o producerFactroy no KafkaTemplate é o Spring, não precisa
        //chamar o método producerFactory(), recebemos como parametro
    }





}
