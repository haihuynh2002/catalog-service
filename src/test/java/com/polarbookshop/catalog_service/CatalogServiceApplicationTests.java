package com.polarbookshop.catalog_service;

import com.polarbookshop.catalog_service.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenBookCreated() {
		var book = new Book("1234567892", "Title", "Author", 9.9);
		webTestClient.post()
				.uri("/books")
				.bodyValue(book)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class)
				.value(acutalBook -> {
					Assertions.assertThat(acutalBook).isNotNull();
					Assertions.assertThat(acutalBook).isEqualTo(book);
				});
	}

}
