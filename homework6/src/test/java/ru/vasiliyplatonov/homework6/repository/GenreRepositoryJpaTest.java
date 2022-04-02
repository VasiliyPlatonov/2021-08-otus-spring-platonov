package ru.vasiliyplatonov.homework6.repository;


import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vasiliyplatonov.homework6.ExpectedDataHolder.EXPECTED_GENRE_1;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
public class GenreRepositoryJpaTest {

	@Autowired
	GenreRepositoryJpa genreRepository;

	@Test
	@DisplayName("should return correct genre by its name")
	void shouldReturnGenreExistingByName() {
		val actualGenreOpt = genreRepository.getByName(EXPECTED_GENRE_1.getName());

		assertThat(actualGenreOpt)
				.usingRecursiveComparison()
				.isEqualTo(Optional.of(EXPECTED_GENRE_1));
	}
}
