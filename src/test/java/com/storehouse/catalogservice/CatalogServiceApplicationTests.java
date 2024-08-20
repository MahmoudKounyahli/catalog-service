package com.storehouse.catalogservice;

import com.storehouse.catalogservice.domain.Part;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}

	@Test
	void whenPostRequestThenPartCreated() {
		var expectedPart = Part.of("0123456789", "name", "description", 5.5,"company", 4, "category");

		webTestClient
				.post()
				.uri("/parts")
				.bodyValue(expectedPart)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Part.class).value(actualPart -> {
					assertThat(actualPart).isNotNull();
					assertThat(actualPart.partNumber()).isEqualTo(expectedPart.partNumber());
				});
	}

}
