package com.andreibarroso.springionic.repositories;

import com.andreibarroso.springionic.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
