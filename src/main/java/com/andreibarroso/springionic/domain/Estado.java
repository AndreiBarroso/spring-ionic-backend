package com.andreibarroso.springionic.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Estado implements Serializable {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String nome;

    @JsonBackReference
    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades = new ArrayList<>();

    public Estado () {

    }

    public Estado(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
