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
@Import({BookRepositoryJpa.class})
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
	@DisplayName("should correct persist book with a single and existing author and genre")
	void shouldCorrectPersistBook() {
		val existingAuthor1 = em.find(Author.class, 1L);
		val existingAuthor2 = em.find(Author.class, 2L);
		val existingGenre1 = em.find(Genre.class, 1L);
		val existingGenre2 = em.find(Genre.class, 2L);

		val expectedBook = bookRepository.add(
				new Book("Some new title",
						List.of(existingAuthor1, existingAuthor2),
						List.of(existingGenre1, existingGenre2))
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
	@DisplayName("should correct persist book with new author and genre")
	void shouldCorrectPersistBookWithNewAuthorAndGenre() {

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
}