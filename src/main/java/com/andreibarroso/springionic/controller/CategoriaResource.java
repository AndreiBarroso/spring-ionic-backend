package com.andreibarroso.springionic.controller;


import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.repositories.CategoriaRepository;
import com.andreibarroso.springionic.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @RequestMapping (value = "/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> find (@PathVariable Integer id) {

        Categoria obj = categoriaService.buscar(id);

        return ResponseEntity.ok().body(obj);
    }

}
