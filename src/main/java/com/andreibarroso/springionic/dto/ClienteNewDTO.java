package com.andreibarroso.springionic.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;


import java.io.Serializable;

@Data
public class ClienteNewDTO implements Serializable {

    private String nome;
    @Email(message = "Coloque um e-mail válido")
    private String email;
    @CPF
    private String cpfOuCnpj;
    private Integer tipo;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Integer cidadeId;

}
