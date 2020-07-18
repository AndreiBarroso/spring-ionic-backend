package com.andreibarroso.springionic.controller;


import com.andreibarroso.springionic.domain.Pedido;
import com.andreibarroso.springionic.repositories.PedidoRepository;
import com.andreibarroso.springionic.services.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    @PostMapping
    public ResponseEntity<Void> criar (@RequestBody @Valid Pedido obj) {

        obj = pedidoService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Pedido>> findPage (
            @RequestParam (value = "page", defaultValue="0") Integer page,
            @RequestParam (value = "linesPerPage", defaultValue="24")Integer linesPerPage,
            @RequestParam (value = "orderBy", defaultValue="instante")String orderBy,
            @RequestParam (value = "direction", defaultValue="DESC")String direction) {
        Page<Pedido> list = pedidoService.findPage(page, linesPerPage, orderBy, direction);

        return ResponseEntity.ok().body(list);

    }

}
