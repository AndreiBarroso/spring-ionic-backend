package com.andreibarroso.springionic.domain;

import com.andreibarroso.springionic.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Cliente implements Serializable {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String nome;
    @Email
    private String email;
    @CPF
    private String cpfOuCnpj;
    private Integer tipo;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente")
    private final List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private  final Set<String> telefones = new HashSet<>();

    public Cliente () {

    }

    public Cliente(Integer id, String nome, @Email String email, @CPF String cpfOuCnpj, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo.getCod();
    }

    public TipoCliente getTipo ()  {
        return TipoCliente.toEnum(tipo);
    }
   public void setTipo (TipoCliente tipo) {
        this.tipo = tipo.getCod ();
   }
}
