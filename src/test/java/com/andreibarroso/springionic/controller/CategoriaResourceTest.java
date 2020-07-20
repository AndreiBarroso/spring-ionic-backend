package com.andreibarroso.springionic.controller;

import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.repositories.CategoriaRepository;
import com.andreibarroso.springionic.services.CategoriaService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.Is.is;


@ExtendWith(MockitoExtension.class)
class CategoriaResourceTest {


	@InjectMocks
	private CategoriaResource categoriaResource;

	@Mock
	private CategoriaService categoriaService;


	@Test
	public void categoriasListaGet() {

		when().
				get("/categorias").
				then().statusCode(200).
				body("id[0]", is(1)).
				body("id[1]",is( 2));

	}

	@Test
	public void CriarCategoriaPost (){
		Categoria categoria= new Categoria (null , "cozinha");

		given()
				.body(categoria).contentType(ContentType.JSON).
				when().
				post("/categorias").
				then().statusCode(201);

	}


	@Test
	public void testeBuscar () {
		ResponseEntity<Categoria> resultados = categoriaResource.buscar(1);
		Mockito.verify(categoriaService).find(1);
		Assertions.assertEquals(HttpStatus.OK, resultados.getStatusCode());

		System.out.println(resultados + "Busca com Sucesso");

	}

}