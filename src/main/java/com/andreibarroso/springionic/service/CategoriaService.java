package com.andreibarroso.springionic.service;

import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public Categoria buscar (Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);

        return  obj.orElse(null);
    }
}
