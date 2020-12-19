package com.andreibarroso.springionic.dto;

import com.andreibarroso.springionic.services.validation.ClienteInsert;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ClienteInsert
@Data
public class ClienteNewDTO implements Serializable {


    @NotEmpty(message="Preenchimento obrigatório")
    @Size(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message = "Coloque um e-mail válido")
    private String email;

    @NotEmpty(message="Preenchimento obrigatório")
    private String cpfOuCnpj;
    private Integer tipo;

    @NotEmpty
    private String senha;

    @NotEmpty(message="Preenchimento obrigatório")
    private String logradouro;

    @NotEmpty(message="Preenchimento obrigatório")
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Integer cidadeId;

}
