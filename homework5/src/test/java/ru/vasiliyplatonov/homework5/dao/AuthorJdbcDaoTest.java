package ru.vasiliyplatonov.homework5.dao;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.vasiliyplatonov.homework5.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@JdbcTest
@DisplayName("Author JDBC Dao")
@Import(AuthorJdbcDao.class)
class AuthorJdbcDaoTest {

	@Autowired
	private AuthorJdbcDao authorDao;


	@Test
	@DisplayName("should return expected author by his id")
	void shouldReturnExpectedAuthorById() {
		val existingId = 1;
		val expectedAuthor = new Author(existingId, "Stephen", "King");
		val actualAuthor = authorDao.getById(existingId);

		assertThat(actualAuthor)
				.usingRecursiveComparison()
				.isEqualTo(expectedAuthor);
	}

	@Test
	@DisplayName("should return correct author existing by id")
	void shouldReturnCorrectAuthorExistingById() {
		val existingId = 1;
		val notExistingId = 1200;
		val expectedTrueResult = authorDao.existsById(existingId);
		val expectedFalseResult = authorDao.existsById(notExistingId);

		assertThat(expectedTrueResult).isTrue();
		assertThat(expectedFalseResult).isFalse();
	}

	@Test
	@DisplayName("should return correct author existing by id")
	void shouldReturnCorrectAuthorExistingByFirstNameAndLastName() {
		val expectedTrueResult = authorDao.existsByFirstNameAndLastName("Stephen", "King");
		assertThat(expectedTrueResult).isTrue();
	}


	@Test
	@DisplayName("should return expected author by his first and last name")
	void shouldReturnExpectedAuthorByFirstNameAndLastName() {
		val existingId = 1;
		val expectedAuthor = new Author(existingId, "Stephen", "King");
		val actualAuthor = authorDao.getByFirstNameAndLastName(
				expectedAuthor.getFirstName(),
				expectedAuthor.getLastName());

		assertThat(actualAuthor)
				.usingRecursiveComparison()
				.isEqualTo(expectedAuthor);
	}

	@Test
	@DisplayName("should return all expected authors")
	void shouldReturnAllExpectedAuthors() {
		val expectedAuthors = List.of(
				new Author(1, "Stephen", "King"),
				new Author(2, "Mikhail", "Lermontov"));

		val actualAuthors = authorDao.getAll();

		assertThat(actualAuthors)
				.usingFieldByFieldElementComparator()
				.isEqualTo(expectedAuthors);
	}

	@Test
	@DisplayName("should correct add author in DB")
	void shouldCorrectAddAuthor() {
		val expectedAuthor = new Author(0, "Walter", "Whitman");

		val actualId = authorDao.add(expectedAuthor);
		val actualAuthor = authorDao.getById(actualId);

		assertThat(actualAuthor)
				.usingRecursiveComparison()
				.ignoringFields("id")
				.isEqualTo(expectedAuthor);
	}


	@Test
	@DisplayName("should correct delete author from DB by id")
	void shouldCorrectDeleteAuthorById() {
		val existingAuthorId = 1;

		authorDao.deleteById(existingAuthorId);

		assertThatThrownBy(() -> authorDao.getById(existingAuthorId))
				.isInstanceOf(EmptyResultDataAccessException.class);
	}

	@Test
	@DisplayName("should correct update author")
	void shouldCorrectUpdateAuthor() {
		val existingId = 1;
		val notUpdatedAuthor = authorDao.getById(existingId);

		val expectedAuthor = new Author(
				notUpdatedAuthor.getId(),
				"Updated first name",
				"Updated last name");

		authorDao.update(expectedAuthor);

		val updatedAuthor = authorDao.getById(existingId);

		assertThat(updatedAuthor)
				.usingRecursiveComparison()
				.isNotEqualTo(notUpdatedAuthor)
				.isEqualTo(expectedAuthor);
	}
}