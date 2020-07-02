package com.andreibarroso.springionic.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Categoria  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Length(min= 5, max=80, message="tamanho deve ter entre 5 a 80 caracteres")
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<>();

    public Categoria (){

    }


    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
