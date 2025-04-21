package com.polarbookshop.catalog_service.web;

import com.polarbookshop.catalog_service.domain.BookNotFoundException;
import com.polarbookshop.catalog_service.domain.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BookController.class)
public class BookControllerMvcTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        var bookIsbn = "1234567891";
        BDDMockito.given(bookService.viewBookDetails(bookIsbn))
                .willThrow(BookNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + bookIsbn))
                .andExpect(MockMvcResultMatchers.status().isNotFound());


    }
}
