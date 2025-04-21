package com.polarbookshop.catalog_service.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonbTester;

import java.io.IOException;

@JsonTest
public class BookJsonTests {

    @Autowired
    JacksonTester<Book> json;

    @Test
    void testSerialize() throws IOException {
        var book = Book.of("123456789", "Title", "Author", 9.9);
        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathValue("@.title").isEqualTo(book.title());
    }

    @Test
    void testDeserialize() throws IOException {
        var content = """
        {
            "isbn": "1234567890",
            "title": "Title",
            "author": "Author",
            "price": 9.90
        }
        """;

        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(Book.of("1234567890", "Title", "Author", 9.90));
    }
}
