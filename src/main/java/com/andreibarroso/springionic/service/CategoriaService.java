package com.andreibarroso.springionic.service;

import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.exceptions.ObjectNotFoundException;
import com.andreibarroso.springionic.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public Categoria find(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria salvar(Categoria categoria) {
        Categoria categoria1 = categoriaRepository.findByid(categoria.getId());

        if (categoria1 != null && !categoria1.equals(categoria)) {
            throw new ObjectNotFoundException("Já existe uma categproa cadastrado com este id.");
        }
        return categoriaRepository.save(categoria);
    }
}

