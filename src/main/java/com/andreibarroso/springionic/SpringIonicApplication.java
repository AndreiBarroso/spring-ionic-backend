package com.andreibarroso.springionic;

import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.repositories.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner {


	private CategoriaRepository categoriaRepository;

	public SpringIonicApplication (CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			Categoria cat1 = new Categoria(null, "informatica");
			Categoria cat2 = new Categoria(null, "escritorio");

			categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}
}
