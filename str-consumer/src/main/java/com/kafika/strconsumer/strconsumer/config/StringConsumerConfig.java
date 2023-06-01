package com.kafika.strconsumer.strconsumer.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.RecordInterceptor;

import java.util.HashMap;

@RequiredArgsConstructor
@Log4j2
@Configuration
public class StringConsumerConfig {

    private final KafkaProperties properties;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        var configs= new HashMap<String, Object>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> strContainerFactory(
            ConsumerFactory<String,String> consumerFactory
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
    //intercepta os registros enviados para um determiando topico, podendo fazer validação
    //quando um registro for enviado para um topico e definir se o lister faz o processamento
    //ou não
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> validMessageContainerFactory(
            ConsumerFactory<String,String> consumerFactory
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory);
        //inerceptador de registros
        factory.setRecordInterceptor(validMessage());
        return factory;
    }

    //antes da msg ser consumida pelo listener valida a msg consumida possui a palavra "Teste"
    //podendo fazer algum processamento. por exemplo: validar uma compra, a idade de uma pessoa
    //fzer uma regra de negocio
    private RecordInterceptor<String, String> validMessage() {
        return (record, consumer) -> {
            if(record.value().contains("Teste")) {
                log.info("Possui a palavra Teste");
                return record;
            }
            return record;
        };
    }
}
