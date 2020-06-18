package com.andreibarroso.springionic.controller;


import com.andreibarroso.springionic.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {


    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar () {

        Categoria novacat = new Categoria(1, "informatica");
        Categoria novacat2 = new Categoria(2, "escritorio");

        List<Categoria> lista = new ArrayList<>();
        lista.add(novacat);
        lista.add(novacat2);

        return lista;
    }
}
