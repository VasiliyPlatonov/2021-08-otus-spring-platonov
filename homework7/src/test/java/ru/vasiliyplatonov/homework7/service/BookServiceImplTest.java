package ru.vasiliyplatonov.homework7.service;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.vasiliyplatonov.homework7.domain.Author;
import ru.vasiliyplatonov.homework7.domain.Book;
import ru.vasiliyplatonov.homework7.domain.Genre;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static ru.vasiliyplatonov.homework7.ExpectedDataHolder.*;

@DisplayName("BookServiceImpl tests: ")
@SpringBootTest
@Sql(scripts = "/sql/data.sql")
@Sql(scripts = "/sql/cleanup-data.sql", executionPhase = AFTER_TEST_METHOD)
class BookServiceImplTest {

	@Autowired
	private BookServiceImpl bookService;

	@Test
	@DisplayName("should return all books with full data")
	void shouldReturnAllBooks() {
		val actualBooks = bookService.findAll();

		assertThat(actualBooks)
				.usingRecursiveComparison()
				.ignoringFields("authors.books")
				.isEqualTo(EXPECTED_BOOKS);
	}

	@Test
	@DisplayName("should return book by id")
	void shouldReturnBookById() {
		val actualBook = bookService.findById(EXPECTED_BOOK_1.getId()).orElseThrow();

		assertThat(actualBook)
				.usingRecursiveComparison()
				.ignoringFields("id", "authors")
				.isEqualTo(EXPECTED_BOOK_1);
	}

	@Test
	@DisplayName("should return book by id with all info")
	void shouldReturnBookByIdWithAllInfo() {
		val actualBook = bookService.findById(EXPECTED_BOOK_1.getId()).orElseThrow();

		assertThat(actualBook)
				.usingRecursiveComparison()
				.ignoringFields("authors.books")
				.isEqualTo(EXPECTED_BOOK_1);
	}


	@Test
	@DisplayName("should return books by its genre")
	void shouldReturnBooksByGenre() {
		val actualBooks = bookService.findByGenre(EXPECTED_GENRE_1);

		assertThat(actualBooks)
				.hasSizeGreaterThan(0)
				.usingRecursiveComparison()
				.ignoringFields("authors", "genres")
				.isEqualTo(List.of(EXPECTED_BOOK_1));
	}

	@Test
	@DisplayName("should return books by its genre name")
	void shouldReturnBooksByGenreName() {
		val actualBooks = bookService.findByGenreName(EXPECTED_GENRE_1.getName());

		assertThat(actualBooks)
				.hasSizeGreaterThan(0)
				.usingRecursiveComparison()
				.ignoringFields("authors", "genres")
				.isEqualTo(List.of(EXPECTED_BOOK_1));
	}

	@Test
	@DisplayName("should return books by author firstname and lastname")
	void shouldReturnBooksByAuthorFirstNameAndLasName() {
		val actualBooks = bookService.findByAuthorFirstNameAndLastName(EXPECTED_AUTHOR_1.getFirstName(), EXPECTED_AUTHOR_1.getLastName());

		assertThat(actualBooks)
				.hasSizeGreaterThan(0)
				.usingRecursiveComparison()
				.ignoringFields("authors")
				.isEqualTo(EXPECTED_AUTHOR_1.getBooks());
	}

	@Test
	@DisplayName("should return books by title")
	void shouldReturnBookByTitle() {
		val actualBooks = bookService.findByTitle(EXPECTED_BOOK_1.getTitle());

		assertThat(actualBooks)
				.hasSizeGreaterThan(0)
				.usingRecursiveComparison()
				.ignoringFields("authors", "genres")
				.isEqualTo(List.of(EXPECTED_BOOK_1));
	}

	@Test
	@DisplayName("should correct persist book with a single and existing author and genre")
	void shouldCorrectPersistBook() {
		val expectedTitle = "Some new title";
		val expectedAuthors = Set.of(new Author(null, EXPECTED_AUTHOR_1.getFirstName(), EXPECTED_AUTHOR_1.getFirstName()));
		val expectedGenres = Set.of(new Genre(null, EXPECTED_GENRE_1.getName()));

		val expectedBook = bookService.add(new Book(
				expectedTitle,
				expectedAuthors,
				expectedGenres));

		val actualBook = bookService.findById(expectedBook.getId()).orElseThrow();

		assertThat(actualBook)
				.isNotNull()
				.usingRecursiveComparison()
				.ignoringFields("authors.id", "authors.books", "genres.id")
				.isEqualTo(expectedBook);
	}

	@Test
	@DisplayName("should correct update book with existing and not author and genre")
	void shouldCorrectUpdateBook() {
		val bookId = EXPECTED_BOOK_1.getId();
		val expectedBook = bookService.findById(bookId).orElseThrow();

		val existingAuthor = (Author) expectedBook.getAuthors().toArray()[0];
		val existingGenre = (Genre) expectedBook.getGenres().toArray()[0];
		val notExistingAuthor = new Author("Name", "LastName");
		val notExistingGenre = new Genre("new genre");

		existingAuthor.setFirstName("new name");
		existingGenre.setName("new name");
		expectedBook.getAuthors().add(notExistingAuthor);
		expectedBook.getGenres().add(notExistingGenre);

		bookService.update(expectedBook);

		val actualBook = bookService.findById(bookId).orElseThrow();

		assertThat(actualBook)
				.isNotNull()
				.usingRecursiveComparison()
				.ignoringFields("authors.books", "authors.id", "genres.id")
				.isEqualTo(expectedBook);
	}

	@Test
	@DisplayName("should correct delete book by id")
	void shouldCorrectDeleteBookById() {
		val bookId = EXPECTED_BOOK_1.getId();
		bookService.deleteById(bookId);

		val actualBookOpt = bookService.findById(bookId);

		assertThat(actualBookOpt).isEmpty();
	}

}





















