package com.andreibarroso.springionic.services;


import com.andreibarroso.springionic.domain.Cliente;
import com.andreibarroso.springionic.dto.ClienteDTO;
import com.andreibarroso.springionic.exceptions.DateIntegrityException;
import com.andreibarroso.springionic.exceptions.ObjectNotFoundException;
import com.andreibarroso.springionic.repositories.ClienteRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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

    @Transactional
    public Cliente salvar(Cliente Cliente) {
        return clienteRepository.save(Cliente);
    }

    public void deletar (Integer id) {
        findCliente(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DateIntegrityException("Não é possivel excluir uma Cliente com produtos");
        }
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = findCliente(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }


    public List<Cliente> findAll() {
        return  clienteRepository.findAll();
    }

    /*
    método para paginação
     */

    public Page<Cliente> findPage (Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return  clienteRepository.findAll(pageRequest);
    }


    /*
    transformar uma entidade em DTO
     */
    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }



}


