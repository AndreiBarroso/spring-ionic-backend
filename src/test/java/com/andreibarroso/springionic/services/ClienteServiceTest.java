package com.andreibarroso.springionic.services;

import com.andreibarroso.springionic.domain.*;
import com.andreibarroso.springionic.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {


    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;


    @Test
    public void testSalvarComSucesso () {

    }

    @Test
    public void testListarTodosClientes () {
        List<Cliente> resultados = clienteService.findAll();
        Mockito.verify(clienteRepository).findAll();
    }

    @Test
    public void testBuscarClienteExistente () {
        Mockito.when(clienteRepository.findById(10)).thenReturn(Optional.of(new Cliente()));
        Cliente resultado = clienteService.findCliente(10);
    }

    @Test
    public void testDeletarCliente() {
        Mockito.when(clienteRepository.findById(1)).thenReturn(Optional.of(new Cliente()));
        clienteService.delete(1);
        verify(clienteRepository, times(1)).deleteById(1);

    }


}