package ru.vasiliyplatonov.homework6.repository;


import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.vasiliyplatonov.homework6.ExpectedDataHolder.EXPECTED_AUTHOR_1;

@DataJpaTest
@DisplayName("Author JPA repository ")
@Import(AuthorRepositoryJpa.class)
public class AuthorRepositoryJpaTest {

	@Autowired
	AuthorRepositoryJpa authorRepository;

	@Test
	@DisplayName("should correct return author by its firstname and lastname")
	void shouldCorrectReturnAuthorByFirstnameAndLastname() {
		val authorOptional = authorRepository.getByFirstNameAndLastName(
				EXPECTED_AUTHOR_1.getFirstName(),
				EXPECTED_AUTHOR_1.getLastName());

		assertThat(authorOptional)
				.usingRecursiveComparison()
				.isEqualTo(Optional.of(EXPECTED_AUTHOR_1));
	}

}
