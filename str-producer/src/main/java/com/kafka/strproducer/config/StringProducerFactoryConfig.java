package com.kafka.strproducer.config;


import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class StringProducerFactoryConfig {

    private final KafkaProperties properties;

    //vair serializar(transf. em um array de bytes) os dados do produtor para o kafka
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        var configs = new HashMap<String, Object>();
        //pega localhost:29092 do application.yml
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        //define o objeto que vai fazer a serialização da nossa chave.Temos chave e valor na msg, precisa
        //ter um serializador para esta chave: StringSerializer.class
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //definir o serializar do valor da msg
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    //Agora definir o kafka template para usar o metodo producerFactory() para poder eviar msg para os topicos
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);//como existe a anotação @Bean no metodo producerFactory
        //acima que vai ficar reponsável por injetar o producerFactroy no KafkaTemplate é o Spring, não precisa
        //chamar o método producerFactory(), recebemos como parametro
    }





}
