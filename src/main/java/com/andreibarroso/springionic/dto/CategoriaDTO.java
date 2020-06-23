package com.andreibarroso.springionic.dto;

import com.andreibarroso.springionic.domain.Categoria;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoriaDTO implements Serializable {
    private Integer id;

    private String nome;


    public CategoriaDTO() {

    }

    public CategoriaDTO(Categoria cat) {
       id = cat.getId();
       nome = cat.getNome();
    }
}
