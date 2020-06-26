package com.andreibarroso.springionic.controller;


import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.dto.CategoriaDTO;
import com.andreibarroso.springionic.repositories.CategoriaRepository;
import com.andreibarroso.springionic.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarTodos () {
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaDTO> listDto = list.stream()
                .map(CategoriaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);

    }
    @GetMapping (value = "/{id}")
    public ResponseEntity<Categoria> buscar (@PathVariable Integer id) {

        Categoria obj = categoriaService.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert (@RequestBody @Valid CategoriaDTO objDto) {

        Categoria obj = categoriaService.fromDTO(objDto);
        obj = categoriaService.salvar(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar (@Valid @PathVariable Integer id, @RequestBody CategoriaDTO objDto) {
        if (!categoriaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Categoria obj = categoriaService.fromDTO(objDto);
        obj.setId(id);
        obj = categoriaService.salvar(obj);

        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        if(!categoriaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage (
            @RequestParam (value = "page", defaultValue="0") Integer page,
            @RequestParam (value = "linesPerPage", defaultValue="24")Integer linesPerPage,
            @RequestParam (value = "orderBy", defaultValue="nome")String orderBy,
            @RequestParam (value = "direction", defaultValue="ASC")String direction) {
        Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> listDto = list.map(CategoriaDTO::new);

        return ResponseEntity.ok().body(listDto);

    }


}




