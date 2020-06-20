package com.andreibarroso.springionic;

import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.domain.Produto;
import com.andreibarroso.springionic.repositories.CategoriaRepository;
import com.andreibarroso.springionic.repositories.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner {


	private CategoriaRepository categoriaRepository;
	private ProdutoRepository produtoRepository;

	public SpringIonicApplication (CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "escritorio");
		Categoria cat2 = new Categoria(null, "informatica");

		Produto p1 = new Produto(null, "COMPUTADOR", 200.00);
		Produto p2 = new Produto(null, "IMPRESSORA", 300.00);
		Produto p3 = new Produto(null, "MOUSE", 1400.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}
}
