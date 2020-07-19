package com.andreibarroso.springionic.controller;

import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.services.CategoriaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@ExtendWith(MockitoExtension.class)
class CategoriaResourceTest {


	@InjectMocks
	private CategoriaResource categoriaResource;

	@Mock
	private CategoriaService categoriaService;

	@Test
	public void testeBuscar () {
		ResponseEntity<Categoria> resultados = categoriaResource.buscar(1);
		Mockito.verify(categoriaService).find(1);
		Assertions.assertEquals(HttpStatus.OK, resultados.getStatusCode());

		System.out.println(resultados + "Busca com Sucesso");

	}

}