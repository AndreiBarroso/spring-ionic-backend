package com.andreibarroso.springionic.controller;

import com.andreibarroso.springionic.dto.EmailDTO;
import com.andreibarroso.springionic.security.JWTUtil;
import com.andreibarroso.springionic.security.UserSS;
import com.andreibarroso.springionic.services.AuthService;
import com.andreibarroso.springionic.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/*
Refresh Token
 */


@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	private final JWTUtil jwtUtil;

	private final AuthService authService;

	public AuthController ( JWTUtil jwtUtil, AuthService authService) {
		this.authService = authService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping(value="/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value="/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO obtDto) {
		authService.sendNewPassword(obtDto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
