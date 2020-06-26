package com.andreibarroso.springionic.dto;

import com.andreibarroso.springionic.domain.Categoria;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class CategoriaDTO implements Serializable {

    private Integer id;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;


    public CategoriaDTO() {

    }
    /*
    passando uma categoria dto para uma categoria
     */

    public CategoriaDTO(Categoria cat) {
       id = cat.getId();
       nome = cat.getNome();
    }
}
