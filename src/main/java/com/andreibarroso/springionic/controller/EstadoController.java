package com.andreibarroso.springionic.controller;


import com.andreibarroso.springionic.domain.Cidade;
import com.andreibarroso.springionic.domain.Estado;
import com.andreibarroso.springionic.dto.CidadeDTO;
import com.andreibarroso.springionic.dto.EstadoDTO;
import com.andreibarroso.springionic.services.CidadeService;
import com.andreibarroso.springionic.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {


	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeService cidadeService;


	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll () {

		List<Estado> list = estadoService.findAll();
		List<EstadoDTO> listDto = list.stream().map(EstadoDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findAll (@PathVariable Integer estadoId) {

		List<Cidade> list = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listDto = list.stream().map(CidadeDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

}
