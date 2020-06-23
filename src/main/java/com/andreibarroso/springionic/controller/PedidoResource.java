package com.andreibarroso.springionic.controller;


import com.andreibarroso.springionic.domain.Pedido;
import com.andreibarroso.springionic.repositories.PedidoRepository;
import com.andreibarroso.springionic.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {


    private PedidoService pedidoService;
    private PedidoRepository pedidoRepository;

    public PedidoResource (PedidoService pedidoService, PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
        this.pedidoService = pedidoService;

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> buscar (@PathVariable Integer id) {
            Pedido obj = pedidoService.findPedido(id);
            return ResponseEntity.ok().body(obj);

    }

}
