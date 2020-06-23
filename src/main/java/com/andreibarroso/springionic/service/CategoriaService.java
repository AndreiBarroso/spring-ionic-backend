package com.andreibarroso.springionic.service;

import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.exceptions.DateIntegrityException;
import com.andreibarroso.springionic.exceptions.ObjectNotFoundException;
import com.andreibarroso.springionic.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        return categoriaRepository.save(categoria);
    }

    public void deletar (Integer id) {
        find(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
                throw new DateIntegrityException("Não é possivel excluir uma categoria com produtos");
        }
    }

}

