package com.andreibarroso.springionic.services;


import com.andreibarroso.springionic.domain.Cliente;
import com.andreibarroso.springionic.domain.Estado;
import com.andreibarroso.springionic.exceptions.ObjectNotFoundException;
import com.andreibarroso.springionic.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> findAll () {

			return estadoRepository.findAllByOrderByNome();

	}


}
