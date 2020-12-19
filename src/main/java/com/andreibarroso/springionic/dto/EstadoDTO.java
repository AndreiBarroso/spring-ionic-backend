package com.andreibarroso.springionic.dto;

import com.andreibarroso.springionic.domain.Estado;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
public class EstadoDTO {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	private String nome;


	public EstadoDTO (Estado obj) {
		id = obj.getId();
		nome = obj.getNome();

	}

}
