package com.andreibarroso.springionic.repositories;

import com.andreibarroso.springionic.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, Integer> {
}
