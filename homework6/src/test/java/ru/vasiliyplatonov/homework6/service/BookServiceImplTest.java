package ru.vasiliyplatonov.homework6.service;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vasiliyplatonov.homework6.ExpectedDataHolder.EXPECTED_BOOKS;

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


}