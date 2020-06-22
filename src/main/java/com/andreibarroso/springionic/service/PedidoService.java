package com.andreibarroso.springionic.service;

import com.andreibarroso.springionic.domain.Cliente;
import com.andreibarroso.springionic.domain.Pedido;
import com.andreibarroso.springionic.exceptions.ObjectNotFoundException;
import com.andreibarroso.springionic.repositories.ClienteRepository;
import com.andreibarroso.springionic.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoService (PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido findPedido(Integer id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }


}



