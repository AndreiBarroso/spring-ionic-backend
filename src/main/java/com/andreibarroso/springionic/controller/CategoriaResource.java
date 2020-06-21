package com.andreibarroso.springionic.controller;


import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.repositories.CategoriaRepository;
import com.andreibarroso.springionic.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping (value = "/{id}")
    public ResponseEntity<?> buscar (@PathVariable Integer id) {

        Categoria obj = categoriaService.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    public Categoria adicionar (@Valid @RequestBody Categoria categoria) {
        return categoriaService.salvar(categoria);
    }


}


