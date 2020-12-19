package com.andreibarroso.springionic.services;


import com.andreibarroso.springionic.domain.Cidade;
import com.andreibarroso.springionic.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	public List<Cidade> findByEstado (Integer estadoId) {

			return cidadeRepository.findCidades(estadoId);

	}


}
