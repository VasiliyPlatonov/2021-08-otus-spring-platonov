package ru.vasiliyplatonov.homework6.repository;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.vasiliyplatonov.homework6.domain.Author;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vasiliyplatonov.homework6.ExpectedDataHolder.*;

@DataJpaTest
@DisplayName("Book JPA repository ")
@Import({BookRepositoryJpa.class, GenreRepositoryJpa.class, AuthorRepositoryJpa.class}) //todo
class BookRepositoryJpaTest {

	private static final int EXPECTED_QUERIES_COUNT = 2;

	@Autowired
	private BookRepositoryJpa bookRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("should return all books with all info")
	void shouldReturnAllBooksWithAllInfo() {
		val actualBooks = bookRepository.getAll();

		assertThat(actualBooks)
				.isNotNull()
				.hasSameSizeAs(EXPECTED_BOOKS)
				.allMatch(b -> b.getGenres() != null && b.getGenres().size() > 0)
				.allMatch(b -> b.getAuthors() != null && b.getAuthors().size() > 0);
	}

	@Test
	@DisplayName("should return all books with all info without N+1 problem")
	void shouldReturnAllBooksWithAllInfoWithoutN1Problem() {

		SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
				.unwrap(SessionFactory.class);
		sessionFactory.getStatistics().setStatisticsEnabled(true);

		System.out.println("\n\n\n\n N+1 ---------------------------------------------------------------------------------------------------");
		val actualBooks = bookRepository.getAll();
		assertThat(actualBooks)
				.isNotNull()
				.hasSameSizeAs(EXPECTED_BOOKS)
				.allMatch(b -> b.getGenres() != null && b.getGenres().size() > 0)
				.allMatch(b -> b.getAuthors() != null && b.getAuthors().size() > 0);
		System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
		assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
	}

	@Test
	@DisplayName("should return book by id")
	void shouldReturnBookById() {
		val actualBook = bookRepository.getById(EXPECTED_BOOK_1.getId());

		assertThat(actualBook)
				.usingRecursiveComparison()
				.isEqualTo(Optional.of(EXPECTED_BOOK_1));
	}

	@Test
	@DisplayName("should return book by id with all info")
	void shouldReturnBookByIdWithAllInfo() {
		System.out.println("--------------------------------------------------");
		val actualBook = bookRepository.getByIdFullyCompleted(EXPECTED_BOOK_1.getId());
		System.out.println("--------------------------------------------------");


		assertThat(actualBook)
				.usingRecursiveComparison()
				.isEqualTo(Optional.of(EXPECTED_BOOK_1));
	}

	@Test
	@DisplayName("should return books by its genre")
	void shouldReturnBooksByGenre() {
		val actualBooks = bookRepository.getByGenre(EXPECTED_GENRE_1);

		assertThat(actualBooks)
				.hasSizeGreaterThan(0)
				.usingRecursiveComparison()
				.isEqualTo(List.of(EXPECTED_BOOK_1));
	}

	@Test
	@DisplayName("should return books by its genre name")
	void shouldReturnBooksByGenreName() {
		val actualBooks = bookRepository.getByGenreName(EXPECTED_GENRE_1.getName());

		assertThat(actualBooks)
				.hasSizeGreaterThan(0)
				.usingRecursiveComparison()
				.ignoringFields("authors")
				.isEqualTo(List.of(EXPECTED_BOOK_1));
	}

	@Test
	@DisplayName("should return books by author firstname and lastname")
	void shouldReturnBooksByAuthorFirstNameAndLasName() {
		val actualBooks = bookRepository
				.getByAuthorFirstNameAndLastName(EXPECTED_AUTHOR_1.getFirstName(), EXPECTED_AUTHOR_1.getLastName());

		assertThat(actualBooks)
				.hasSizeGreaterThan(0)
				.usingRecursiveComparison()
				.ignoringFields("authors", "genres")
				.isEqualTo(EXPECTED_AUTHOR_1.getBooks());
	}

	@Test
	@DisplayName("should correct persist book with existing and not author and genre")
	void shouldCorrectPersistBook() {
		val existingAuthor = new Author(EXPECTED_AUTHOR_1.getFirstName(), EXPECTED_AUTHOR_1.getLastName());
		val notExistingAuthor = new Author("Name", "LastName");
		val existingGenre = new Genre(EXPECTED_GENRE_1.getName());
		val notExistingGenre = new Genre("new genre");

		val expectedBook = bookRepository.add(
				new Book("Some new title",
						List.of(existingAuthor, notExistingAuthor),
						List.of(existingGenre, notExistingGenre))
		);

		em.flush();
		em.clear();

		val actualOptionalBook = bookRepository.getById(expectedBook.getId());

		assertThat(actualOptionalBook)
				.isNotNull()
				.isNotEmpty()
				.get()
				.usingRecursiveComparison()
				.ignoringFields("id", "authors.books")
				.isEqualTo(expectedBook);
	}

	@Test
	@DisplayName("should correct persist book with new authors and genres")
	void shouldCorrectPersistBookWithNewAuthorsAndGenres() {

		val newAuthor1 = new Author(null, "New", "Author1");
		val newAuthor2 = new Author(null, "New", "Author2");
		val newGenre1 = new Genre("New genre1");
		val newGenre2 = new Genre("New genre2");

		val expectedAuthors = List.of(newAuthor1, newAuthor2);
		val expectedGenres = List.of(newGenre1, newGenre2);

		val expectedTitle = "Some new title";
		val expectedBook = new Book(expectedTitle, expectedAuthors, expectedGenres);

		bookRepository.add(expectedBook);

		em.flush();
		em.clear();

		val actualBooks = bookRepository.getByTitle(expectedTitle);
		assertThat(actualBooks)
				.hasSize(1)
				.usingRecursiveComparison()
				.ignoringFields("id")
				.isEqualTo(List.of(expectedBook));
	}

	@Test
	@DisplayName("should correct update book with existing and not author and genre")
	void shouldCorrectUpdateBook() {
		val bookId = EXPECTED_BOOK_1.getId();
		val expectedBook = em.find(Book.class, bookId);

		val existingAuthor = expectedBook.getAuthors().get(0);
		val notExistingAuthor = new Author("Name", "LastName");
		val existingGenre = expectedBook.getGenres().get(0);
		val notExistingGenre = new Genre("new genre");

		existingAuthor.setFirstName("New name");
		existingGenre.setName("New name");
		expectedBook.getGenres().add(notExistingGenre);
		expectedBook.getAuthors().add(notExistingAuthor);

		bookRepository.update(expectedBook);

		em.flush();
		em.clear();

		val actualBook = em.find(Book.class, bookId);

		assertThat(actualBook)
				.isNotNull()
				.usingRecursiveComparison()
				.ignoringFields("id", "authors.books", "authors.id", "genres.id")
				.isEqualTo(expectedBook);
	}

	@Test
	@DisplayName("should correct delete book by id")
	void shouldCorrectDeleteBookById() {
		val bookId = EXPECTED_BOOK_1.getId();

		bookRepository.deleteById(bookId);

		em.flush();
		em.clear();

		val actualBook = em.find(Book.class, bookId);

		assertThat(actualBook).isNull();
	}

	@Test
	@DisplayName("should correct delete book by title")
	void shouldCorrectDeleteBookByTitle() {
		val bookId = EXPECTED_BOOK_1.getId();
		val bookTitle = em.find(Book.class, bookId).getTitle();

		bookRepository.deleteByTitle(bookTitle);

		em.flush();
		em.clear();

		val actualBook = em.find(Book.class, bookId);

		assertThat(actualBook).isNull();
	}

	@Test
	@DisplayName("should correct delete all books in list")
	void shouldCorrectDeleteAllBooksInList() {

		val books = List.of(EXPECTED_BOOK_1, EXPECTED_BOOK_3);

		bookRepository.delete(books);

		em.flush();
		em.clear();

		val actualBook1 = em.find(Book.class, EXPECTED_BOOK_1.getId());
		val actualBook2 = em.find(Book.class, EXPECTED_BOOK_3.getId());


		assertThat(actualBook1).isNull();
		assertThat(actualBook2).isNull();
	}


}