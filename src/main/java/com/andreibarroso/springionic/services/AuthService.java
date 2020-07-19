package com.andreibarroso.springionic.services;

import com.andreibarroso.springionic.domain.Cliente;
import com.andreibarroso.springionic.exceptions.ObjectNotFoundException;
import com.andreibarroso.springionic.repositories.ClienteRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

	private final ClienteRepository clienteRepository;

	private final BCryptPasswordEncoder pe;

	private final EmailService emailService;

	private final Random rand = new Random();

	public AuthService (ClienteRepository clienteRepository, BCryptPasswordEncoder pe, EmailService emailService) {
		this.clienteRepository = clienteRepository;
		this.pe = pe;
		this.emailService = emailService;
	}

	public void sendNewPassword (String email) {

		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null){
			throw  new ObjectNotFoundException("E-mail não encontrado");
		}

		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));

		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char [10];
		for(int i=0; i<10;  i++){
			vet [i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt ==0) { //gera uma digito
			return (char) (rand.nextInt(10)+ 48);
		}
		else if (opt == 1) { //gera  letra maiuscula
			return (char) (rand.nextInt(26)+ 65);
		}
		else { //gera letra minuscula
			return (char) (rand.nextInt(26)+ 97);

		}
	}


}

