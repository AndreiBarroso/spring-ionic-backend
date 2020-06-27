package com.andreibarroso.springionic.controller;

import com.andreibarroso.springionic.domain.Cliente;
import com.andreibarroso.springionic.dto.ClienteDTO;
import com.andreibarroso.springionic.dto.ClienteNewDTO;
import com.andreibarroso.springionic.repositories.ClienteRepository;
import com.andreibarroso.springionic.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> buscarClientePorId (@PathVariable Integer id) {
        Cliente cliente1 = clienteService.findCliente(id);

        return ResponseEntity.ok().body(cliente1);

    }

    @PostMapping
    public ResponseEntity<Void> insert (@Valid @RequestBody ClienteNewDTO objDto) {

        Cliente obj = clienteService.fromDTO(objDto);
        obj = clienteService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar (@Valid @PathVariable Integer id, @RequestBody ClienteDTO objDto) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Cliente obj = clienteService.fromDTO(objDto);
        obj.setId(id);
        obj = clienteService.update(obj);

        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        if(!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos () {
        List<Cliente> list = clienteService.findAll();
        List<ClienteDTO> listDto = list.stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);

    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> findPage (
            @RequestParam (value = "page", defaultValue="0") Integer page,
            @RequestParam (value = "linesPerPage", defaultValue="24")Integer linesPerPage,
            @RequestParam (value = "orderBy", defaultValue="nome")String orderBy,
            @RequestParam (value = "direction", defaultValue="ASC")String direction) {
        Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listDto = list.map(ClienteDTO::new);

        return ResponseEntity.ok().body(listDto);

    }

}
