package com.andreibarroso.springionic;

import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.domain.Cidade;
import com.andreibarroso.springionic.domain.Estado;
import com.andreibarroso.springionic.domain.Produto;
import com.andreibarroso.springionic.repositories.CategoriaRepository;
import com.andreibarroso.springionic.repositories.CidadeRepository;
import com.andreibarroso.springionic.repositories.EstadoRepository;
import com.andreibarroso.springionic.repositories.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner {


	private CategoriaRepository categoriaRepository;
	private ProdutoRepository produtoRepository;
	private EstadoRepository estadoRepository;
	private CidadeRepository cidadeRepository;

	public SpringIonicApplication (CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, EstadoRepository estadoRepository, CidadeRepository cidadeRepository) {
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.cidadeRepository = cidadeRepository;
		this.estadoRepository = estadoRepository;
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

		Estado estado1 = new Estado(null, "Minas gerais");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "Taubate", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado1);


		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));


		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado1.getCidades().addAll(Arrays.asList(cidade2));
		estado2.getCidades().addAll(Arrays.asList(cidade2));


		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}
}
