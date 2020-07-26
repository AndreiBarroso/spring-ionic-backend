package com.andreibarroso.springionic;

import com.andreibarroso.springionic.config.Trello;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;


@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations="classpath:test.properties")
class SpringIonicApplicationTest {

	/*
	consumir api endpoint get
	 */

	@Test
	public void consumirApi () {
		RestTemplate rest =  new RestTemplate();

//		https://api.trello.com/1/boards/5612e4f91b25c15e873722b8?fields=all

		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("api.trello.com")
				.path("1/boards/5612e4f91b25c15e873722b8")
				.queryParam("fiels", "all")
				.build();

    ResponseEntity<Trello> entity =  rest.getForEntity(uri.toUriString(), Trello.class);

		System.out.println(Objects.requireNonNull(entity.getBody()).getName());
		System.out.println(Objects.requireNonNull(entity.getBody()).getId());
		System.out.println(entity.getBody().getIdOrganization());
		System.out.println(entity.getBody().getUrl());
	}


}