package com.andreibarroso.springionic.dto;

import com.andreibarroso.springionic.domain.Cliente;
import com.andreibarroso.springionic.services.validation.ClienteUpdate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;


@ClienteUpdate
public class ClienteDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 5, max = 120, message = "o  tamanho deve ser entre 5 a 120 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "E-mail inválido")
    private String email;


    public ClienteDTO( ) {

    }

    public ClienteDTO(Cliente obj) {
        id= obj.getId();
        nome=obj.getNome();
        email=obj.getEmail();
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
