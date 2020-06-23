package com.andreibarroso.springionic.controller;


import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.repositories.CategoriaRepository;
import com.andreibarroso.springionic.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @GetMapping
    public List<Categoria> listarTodos () {
        return categoriaRepository.findAll();
    }


    @GetMapping (value = "/{id}")
    public ResponseEntity<?> buscar (@PathVariable Integer id) {

        Categoria obj = categoriaService.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria adicionar (@RequestBody Categoria categoria) {
        return categoriaService.salvar(categoria);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar (@PathVariable Integer id, @RequestBody Categoria categoria) {
        if (!categoriaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoria.setId(id);
        categoria = categoriaService.salvar(categoria);

        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        if(!categoriaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}




