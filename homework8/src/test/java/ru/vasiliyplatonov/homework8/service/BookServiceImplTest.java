package ru.vasiliyplatonov.homework8.service;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vasiliyplatonov.homework8.domain.Author;
import ru.vasiliyplatonov.homework8.domain.Book;
import ru.vasiliyplatonov.homework8.domain.Genre;
import ru.vasiliyplatonov.homework8.repository.BookRepository;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vasiliyplatonov.homework8.changelog.BooksInitializer.ExpectedDataHolder.*;

@SpringBootTest
@DisplayName("BookServiceImpl tests: ")
class BookServiceImplTest {

	@Autowired
	private BookServiceImpl bookService;

	@Autowired
	private BookCommentService commentService;

	@Autowired
	private BookRepository bookRepository;

	@BeforeEach
	void beforeEach() {
		bookRepository.saveAll(EXPECTED_BOOKS);
	}

	@AfterEach
	void afterEach() {
		bookRepository.deleteAll();
	}


	@Test
	@DisplayName("should return book by id")
	void findById() {
		val actualBook = bookService.findById(EXPECTED_BOOK_1.getId()).orElseThrow();

		assertThat(actualBook)
				.usingRecursiveComparison()
				.isEqualTo(EXPECTED_BOOK_1);
	}

	@Test
	@DisplayName("should return all books")
	void findAll() {
		val actualBooks = bookService.findAll();

		assertThat(actualBooks)
				.usingRecursiveComparison()
				.isEqualTo(EXPECTED_BOOKS);
	}

	@Test
	@DisplayName("should return books by title")
	void findByTitle() {
		val expectedTitle = EXPECTED_BOOK_1.getTitle();
		val expectedBooks = List.of(EXPECTED_BOOK_1);

		val actualBooks = bookService.findByTitle(expectedTitle);

		assertThat(actualBooks)
				.usingRecursiveComparison()
				.isEqualTo(expectedBooks);
	}

	@Test
	@DisplayName("should return books by author firstname and lastname")
	void findByAuthorFirstNameAndLastName() {
		val authorFirstName = "Stephen";
		val authorLastName = "King";

		val expectedBooks = List.of(EXPECTED_BOOK_1, EXPECTED_BOOK_2);
		val actualBooks = bookService.findByAuthorFirstNameAndLastName(authorFirstName, authorLastName);

		assertThat(actualBooks)
				.usingRecursiveComparison()
				.isEqualTo(expectedBooks);
	}

	@Test
	@DisplayName("should return books by genre")
	void findByGenre() {
		val expectedGenre = EXPECTED_BOOK_1.getGenres().stream().findFirst().orElseThrow();
		val expectedBooks = List.of(EXPECTED_BOOK_1);

		val actualBooks = bookService.findByGenre(expectedGenre);

		assertThat(actualBooks)
				.usingRecursiveComparison()
				.isEqualTo(expectedBooks);

	}

	@Test
	@DisplayName("should return books by genre`s name")
	void findByGenreName() {
		val expectedGenreName = EXPECTED_BOOK_1.getGenres().stream().findFirst().orElseThrow().getName();
		val expectedBooks = List.of(EXPECTED_BOOK_1);

		val actualBooks = bookService.findByGenreName(expectedGenreName);

		assertThat(actualBooks)
				.usingRecursiveComparison()
				.isEqualTo(expectedBooks);
	}

	@Test
	@DisplayName("should correct persist book")
	void add() {
		val expectedTitle = "Some new title";
		val expectedAuthors = Set.of(new Author(EXPECTED_AUTHOR_1.getFirstName(), EXPECTED_AUTHOR_1.getFirstName()));
		val expectedGenres = Set.of(new Genre(EXPECTED_GENRE_1.getName()));

		val expectedBook = bookService.add(new Book(
				expectedTitle,
				expectedAuthors,
				expectedGenres));

		val actualBook = bookService.findById(expectedBook.getId()).orElseThrow();

		assertThat(actualBook)
				.isNotNull()
				.usingRecursiveComparison()
				.ignoringFields("id")
				.isEqualTo(expectedBook);
	}


	@Test
	@DisplayName("should correct update book")
	void update() {
		val bookId = EXPECTED_BOOK_1.getId();
		val expectedBook = bookService.findById(bookId).orElseThrow();

		// update existing
		val existingAuthor = (Author) expectedBook.getAuthors().toArray()[0];
		expectedBook.getAuthors().toArray()[0] = new Author("new name", existingAuthor.getLastName());
		expectedBook.getGenres().toArray()[0] = new Genre("updated genre name");

		// add new author and genre
		val notExistingAuthor = new Author("Name", "LastName");
		val notExistingGenre = new Genre("new genre");
		expectedBook.getAuthors().add(notExistingAuthor);
		expectedBook.getGenres().add(notExistingGenre);

		bookService.update(expectedBook);

		val actualBook = bookService.findById(bookId).orElseThrow();

		assertThat(actualBook)
				.isNotNull()
				.usingRecursiveComparison()
				.isEqualTo(expectedBook);
	}

	@Test
	@DisplayName("should correct delete book by id")
	void deleteById() {
		val bookId = EXPECTED_BOOK_1.getId();
		bookService.deleteById(bookId);

		val actualBookOpt = bookService.findById(bookId);

		assertThat(actualBookOpt).isEmpty();
	}

	@Test
	@DisplayName("should correct delete book`s comments after book deleting")
	void shouldDeleteBookCommentsAfterDeletingBook() {
		val bookId = EXPECTED_BOOK_1.getId();
		bookService.deleteById(bookId);

		val shouldBeEmpty = commentService.findByBookId(bookId);

		assertThat(shouldBeEmpty.isEmpty()).isTrue();
	}
}