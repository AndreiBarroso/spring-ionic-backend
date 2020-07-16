package com.andreibarroso.springionic.services;

import com.andreibarroso.springionic.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class  UserService {

	/*
	metodo para retornar  usuario logado
	 */

	public static UserSS authenticated () {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}

	}
}
