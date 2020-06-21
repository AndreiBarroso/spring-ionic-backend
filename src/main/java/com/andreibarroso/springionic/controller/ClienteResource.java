package com.andreibarroso.springionic.controller;

import com.andreibarroso.springionic.domain.Cliente;
import com.andreibarroso.springionic.repositories.ClienteRepository;
import com.andreibarroso.springionic.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarClientePorId (@PathVariable Integer id) {
        Cliente cliente1 = clienteService.findCliente(id);

        return ResponseEntity.ok().body(cliente1);

    }

}
