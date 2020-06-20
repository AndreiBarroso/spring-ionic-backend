package com.andreibarroso.springionic.repositories;

import com.andreibarroso.springionic.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
