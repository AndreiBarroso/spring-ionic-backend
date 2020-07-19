package com.andreibarroso.springionic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO implements Serializable {

		@Getter
		@Setter
		private String email;

	}

