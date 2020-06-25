package com.andreibarroso.springionic.services;

import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.dto.CategoriaDTO;
import com.andreibarroso.springionic.exceptions.DateIntegrityException;
import com.andreibarroso.springionic.exceptions.ObjectNotFoundException;
import com.andreibarroso.springionic.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

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

    public List<Categoria> findAll() {
        return  categoriaRepository.findAll();
    }

    /*
    método para paginação
     */

    public Page<Categoria> findPage (Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return  categoriaRepository.findAll(pageRequest);
    }


    /*
    transformar uma entidade em DTO
     */
    public Categoria fromDTO(CategoriaDTO objDto) {
        return  new Categoria(objDto.getId(), objDto.getNome());

    }
}


