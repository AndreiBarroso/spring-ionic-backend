package com.andreibarroso.springionic.service;


import com.andreibarroso.springionic.domain.Categoria;
import com.andreibarroso.springionic.domain.Cliente;
import com.andreibarroso.springionic.exceptions.ObjectNotFoundException;
import com.andreibarroso.springionic.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService (ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente findCliente (Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }


}


