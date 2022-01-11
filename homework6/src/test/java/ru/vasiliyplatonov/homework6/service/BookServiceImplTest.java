package ru.vasiliyplatonov.homework6.service;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.vasiliyplatonov.homework6.domain.Author;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.Genre;

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
	@DisplayName("should return book by id with all info")
	void shouldReturnBookByIdWithAllInfo() {
		val actualBook = bookService.getByIdFullyCompleted(EXPECTED_BOOK_1.getId());

		assertThat(actualBook)
				.usingRecursiveComparison()
				.ignoringFields("authors.books")
				.isEqualTo(EXPECTED_BOOK_1);
	}


	@Test
	@DisplayName("should return books by its genre")
	void shouldReturnBooksByGenre() {
		val actualBooks = bookService.getByGenre(EXPECTED_GENRE_1);

		assertThat(actualBooks)
				.hasSizeGreaterThan(0)
				.usingRecursiveComparison()
				.ignoringFields("authors", "genres")
				.isEqualTo(List.of(EXPECTED_BOOK_1));
	}

	@Test
	@DisplayName("should return books by its genre name")
	void shouldReturnBooksByGenreName() {
		val actualBooks = bookService.getByGenreName(EXPECTED_GENRE_1.getName());

		assertThat(actualBooks)
				.hasSizeGreaterThan(0)
				.usingRecursiveComparison()
				.ignoringFields("authors", "genres")
				.isEqualTo(List.of(EXPECTED_BOOK_1));
	}

	@Test
	@DisplayName("should return books by author firstname and lastname")
	void shouldReturnBooksByAuthorFirstNameAndLasName() {
		val actualBooks = bookService.getByAuthorFirstNameAndLastName(EXPECTED_AUTHOR_1.getFirstName(), EXPECTED_AUTHOR_1.getLastName());

		assertThat(actualBooks)
				.hasSizeGreaterThan(0)
				.usingRecursiveComparison()
				.isEqualTo(EXPECTED_AUTHOR_1.getBooks());
	}

	@Test
	@DisplayName("should return books by title")
	void shouldReturnBookByTitle() {
		val actualBooks = bookService.getByTitle(EXPECTED_BOOK_1.getTitle());

		assertThat(actualBooks)
				.hasSizeGreaterThan(0)
				.usingRecursiveComparison()
				.ignoringFields("authors", "genres")
				.isEqualTo(List.of(EXPECTED_BOOK_1));
	}

	@Test
	@Transactional
	@Rollback
	@DisplayName("should correct persist book with a single and existing author and genre")
	void shouldCorrectPersistBook() {
		val expectedTitle = "Some new title";
		val expectedAuthors = List.of(new Author(null, EXPECTED_AUTHOR_1.getFirstName(), EXPECTED_AUTHOR_1.getFirstName()));
		val expectedGenres = List.of(new Genre(null, EXPECTED_GENRE_1.getName()));

		val expectedBook = bookService.add(new Book(
				expectedTitle,
				expectedAuthors,
				expectedGenres));

		val actualBook = bookService.getByIdFullyCompleted(expectedBook.getId());

		assertThat(actualBook)
				.isNotNull()
				.usingRecursiveComparison()
				.ignoringFields("id", "authors.books", "authors.id", "genres.id")
				.isEqualTo(expectedBook);
	}

	@Test
	@Transactional
	@Rollback
	@DisplayName("should correct update book with existing and not author and genre")
	void shouldCorrectUpdateBook() {
		val bookId = EXPECTED_BOOK_1.getId();
		val expectedBook = bookService.getByIdFullyCompleted(bookId);

		val existingAuthor = expectedBook.getAuthors().get(0);
		val existingGenre = expectedBook.getGenres().get(0);
		val notExistingAuthor = new Author("Name", "LastName");
		val notExistingGenre = new Genre("new genre");

		existingAuthor.setFirstName("new name");
		existingGenre.setName("new name");
		expectedBook.getAuthors().add(notExistingAuthor);
		expectedBook.getGenres().add(notExistingGenre);

		bookService.update(expectedBook);

		val actualBook = bookService.getByIdFullyCompleted(bookId);

		assertThat(actualBook)
				.isNotNull()
				.usingRecursiveComparison()
				.ignoringFields("id", "authors.books", "authors.id", "genres.id")
				.isEqualTo(expectedBook);
	}

	@Test
	@Transactional
	@Rollback
	@DisplayName("should correct delete book by id")
	void shouldCorrectDeleteBookById() {
		val bookId = EXPECTED_BOOK_1.getId();

		bookService.deleteById(bookId);

		val actualBook = bookService.getById(bookId);

		assertThat(actualBook).isNull();
	}

	@Test
	@Transactional
	@Rollback
	@DisplayName("should correct delete book by title")
	void shouldCorrectDeleteBookByTitle() {
		val bookId = EXPECTED_BOOK_1.getId();
		val bookTitle = EXPECTED_BOOK_1.getTitle();

		bookService.deleteByTitle(bookTitle);

		val actualBook = bookService.getById(bookId);

		assertThat(actualBook).isNull();
	}
}





















