package ru.vasiliyplatonov.homework5.dao;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.vasiliyplatonov.homework5.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
@DisplayName("Genre JDBC Dao")
@Import(GenreJdbcDao.class)
class GenreJdbcDaoTest {

	private static final int EXPECTED_GENRE_COUNT = 3;
	private static final int EXISTING_GENRE_ID = 1;
	private static final String EXISTING_GENRE_NAME = "drama";

	@Autowired
	private GenreJdbcDao genreDao;

	@Test
	@DisplayName("should return expected genre by its id")
	void shouldReturnExpectedGenreById() {
		val existingId = 2;
		val expectedGenre = new Genre(existingId, "drama");
		val actualGenre = genreDao.getById(existingId);

		assertThat(actualGenre)
				.usingRecursiveComparison()
				.isEqualTo(expectedGenre);
	}

	@Test
	@DisplayName("should return expected genre by its name")
	void shouldReturnExpectedGenreByName() {
		val expectedGenre = new Genre(2, "drama");
		val actualGenre = genreDao.getByName(expectedGenre.getName());

		assertThat(actualGenre)
				.usingRecursiveComparison()
				.isEqualTo(expectedGenre);
	}

	@Test
	@DisplayName("should return correct genre existing by its id")
	void shouldReturnGenreExistingById() {
		val existingId = 2;
		val isGenreExists = genreDao.existsById(existingId);

		assertThat(isGenreExists).isTrue();
	}

	@Test
	@DisplayName("should return correct genre existing by its name")
	void shouldReturnGenreExistingByName() {
		val existingGenreName = "drama";
		val isGenreExists = genreDao.existsByName(existingGenreName);

		assertThat(isGenreExists).isTrue();
	}


	@Test
	@DisplayName("should return all expected genres")
	void shouldReturnAllExpectedGenres() {
		val expectedGenres = List.of(
				new Genre(1, "thriller"),
				new Genre(2, "drama"),
				new Genre(3, "psychological fiction"),
				new Genre(4, "poem")
		);

		val actualGenres = genreDao.getAll();

		assertThat(actualGenres)
				.usingFieldByFieldElementComparator()
				.containsExactlyInAnyOrderElementsOf(expectedGenres);

	}

	@Test
	@DisplayName("should correct add genre in DB")
	void shouldCorrectAddGenre() {
		val expectedGenre = new Genre(0, "adventure");

		val actualId = genreDao.add(expectedGenre);
		val actualGenre = genreDao.getById(actualId);

		assertThat(actualGenre)
				.usingRecursiveComparison()
				.ignoringFields("id")
				.isEqualTo(expectedGenre);
	}

	@Test
	@DisplayName("should correct delete genere from DB by id")
	void shouldCorrectDeleteGenreById() {
		val existingId = 1;
		val deletedGenre = genreDao.getById(existingId);

		genreDao.deleteById(deletedGenre.getId());

		assertThatThrownBy(() -> genreDao.getById(existingId))
				.isInstanceOf(EmptyResultDataAccessException.class);
	}

	@Test
	@DisplayName("should correct update genre")
	void shouldCorrectUpdateGenre() {
		val existingId = 1;
		val existingGenre = genreDao.getById(existingId);
		val expectedGenre = new Genre(existingGenre.getId(), "adventure");

		assertThat(existingGenre.getName())
				.isNotEqualToIgnoringCase(expectedGenre.getName());

		genreDao.update(expectedGenre);
		val actualGenre = genreDao.getById(existingId);

		assertThat(actualGenre)
				.usingRecursiveComparison()
				.isEqualTo(expectedGenre);
	}
}




















