package com.andreibarroso.springionic.controller;

import com.andreibarroso.springionic.security.JWTUtil;
import com.andreibarroso.springionic.security.UserSS;
import com.andreibarroso.springionic.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/*
Refresh Token
 */


@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	private final JWTUtil jwtUtil;

	public AuthController ( JWTUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}


	@PostMapping(value="/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
}
