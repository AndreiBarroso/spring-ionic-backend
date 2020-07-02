package com.andreibarroso.springionic.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Cidade implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    public Cidade () {

    }

    public Cidade(Integer id, String nome, Estado estado) {
        super();
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }
}
