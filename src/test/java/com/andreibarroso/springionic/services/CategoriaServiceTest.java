package com.andreibarroso.springionic.services;

import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.repositories.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {


    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;


    @Test
    public void testSalvarComSucesso () {
        Categoria categoria = new Categoria();
        categoria.setId(1);
        categoria.setNome("Escritorio");
        categoriaService.salvar(categoria);
        verify(categoriaRepository, times(1)).save(categoria);

    }

    @Test
    public void testListar () {
        List<Categoria> resultados = categoriaService.findAll();
        Mockito.verify(categoriaRepository).findAll();
    }

    @Test
    public void testBuscarExistente () {
        Mockito.when(categoriaRepository.findById(1)).thenReturn(Optional.of(new Categoria()));
        Categoria resultado = categoriaService.find(1);
    }

    @Test
    public void testDeletarCategoria () {
        Mockito.when(categoriaRepository.findById(1)).thenReturn(Optional.of(new Categoria()));
        categoriaService.deletar(1);
        verify(categoriaRepository, times(1)).deleteById(1);

    }


}