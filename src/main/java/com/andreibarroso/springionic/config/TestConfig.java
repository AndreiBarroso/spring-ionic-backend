package com.andreibarroso.springionic.config;

import com.andreibarroso.springionic.services.DBService;
import com.andreibarroso.springionic.services.EmailService;
import com.andreibarroso.springionic.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instantiateDatabase () throws ParseException {
		dbService.instantiateTestDatabase();

		return  true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
