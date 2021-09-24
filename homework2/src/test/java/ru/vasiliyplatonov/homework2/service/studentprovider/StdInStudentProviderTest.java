package ru.vasiliyplatonov.homework2.service.studentprovider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("StdInStudentProviderTest service should have: ")
@ExtendWith(MockitoExtension.class)
class StdInStudentProviderTest {

	private StdInStudentProvider studentProvider;

	@Mock
	private Scanner scanner;


	@BeforeEach
	void setUp() {
		given(scanner.nextLine()).willReturn("Name");
		studentProvider = new StdInStudentProvider(scanner);
	}

	@Test
	void shouldHaveCorrectGetStudent() {
		final var student = studentProvider.getStudent();

		verify(scanner, times(2)).nextLine();

		assertThat(student.getFirstName()).isEqualTo("Name");
		assertThat(student.getLastName()).isEqualTo("Name");
	}
}