package ru.vasiliyplatonov.homework6.service;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vasiliyplatonov.homework6.ExpectedDataHolder.*;

@DisplayName("BookServiceImpl tests: ")
@SpringBootTest
class BookServiceImplTest {

	@Autowired
	private BookServiceImpl bookService;

	@Test
	@DisplayName("should return all books with full data")
	void shouldReturnAllBooks() {
		val actualBooks = bookService.getAll();
		assertThat(actualBooks)
				.isNotEmpty()
				.hasSameSizeAs(EXPECTED_BOOKS);
	}

	@Test
	@DisplayName("should return book by id")
	void shouldReturnBookById() {
		val actualBook = bookService.getById(EXPECTED_BOOK_1.getId());

		assertThat(actualBook)
				.usingRecursiveComparison()
				.ignoringFields("authors", "genres")
				.isEqualTo(EXPECTED_BOOK_1);
	}


	@Test
	@DisplayName("should return books by its genre")
	void shouldReturnBooksByGenre() {

		val actualBooks = bookService.getByGenre(EXPECTED_GENRE_1);

		assertThat(actualBooks)
				.hasSizeGreaterThan(0)
				.usingRecursiveComparison()
				.isEqualTo(List.of(EXPECTED_BOOK_1));
	}
}