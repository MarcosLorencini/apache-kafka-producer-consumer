package com.kafka.strproducer;

public class anotacoes {

    //aula 6 - subir as instâncias do zookeeaper, do Apache Kafka e do kafkadrop utilizando o docker compose
    //o arquivo docker-compose.yml possui as informações para subir as 3 intancias:
    // zookeeper precisa subir primeiro para rodar o apache kafka, kafka e o kfdrop
    // o docker-compose up vai subir o container: vai fazer pull das imagens e subir os 3 serviços
    //subindo ir no navegador e acessar o kfdrop(overview do cluster kafka e pode criar topicos)
    // localhost:19000
    //ctr + c estopa o docker, para os 3 serviços
    //docker container ls: verifica quais containers estao rodando
    //docker container ls -a: mostra os que estão rodando e os que já rodaram
    //docker images: são imagens que já foram baixadas
    //docker container up -d : sob as imagens sem deixar o terminal travado(destachar o terminal)
    //docker-compose down : stopa os serviços
}
