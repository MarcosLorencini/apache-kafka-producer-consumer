package com.kafika.strconsumer.strconsumer.custom;


import org.springframework.core.annotation.AliasFor;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//nesta classe estamos criando um listener customizado, para não replicar código na
// classe SrtConsumerListener.java

//informa ao compilador java e para JVM que esta anotação deve ester disponível por meio de reflexão
//tem tempo de exercução
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@KafkaListener
public @interface StrConsumerCustomListener {

    //pode ser usado para decorar atributos dentro de uma anotação
    //atributo topics customizado para o nome padrão "str-topic"
    @AliasFor(annotation = KafkaListener.class, attribute = "topics")
    String[] topics() default "str-topic";

    @AliasFor(annotation = KafkaListener.class, attribute = "containerFactory")
    String containerFactory() default "strContainerFactory";

    //como existe grupos diferentes, não tem com o mencionar um groupId default
    // temos que definir na clase StrConsumerListener
    @AliasFor(annotation = KafkaListener.class, attribute = "groupId")
    String groupId() default "";

    //dentro de @KafkaLister existe um metodo errorHandler() que podemos customizar
    //errorCustomrHandler é bean que o spring vai disponibilizar que é o nome da classe
    //captura exception
    @AliasFor(annotation = KafkaListener.class, attribute = "errorHandler")
    String errorHandler() default "errorCustomrHandler";
}
