package ru.vasiliyplatonov.homework6.repository;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

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
}