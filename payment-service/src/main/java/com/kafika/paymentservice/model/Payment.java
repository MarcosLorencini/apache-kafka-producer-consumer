package com.kafika.paymentservice.model;

import lombok.Getter;

import java.io.Serializable;

@Getter//para criar os metodos acessores, o objeto é serializado antes de ser enviado para o topico e fica armazenado
//serializado em um array de bytes, onde o consumer tem que pegar e deserializar, tem que implementar o seria
//lizable para serializar o objeto.
public class Payment implements Serializable {

    private Long id;
    private Long idUser;
    private Long idProduto;
    private String cardNumber;
}
