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




