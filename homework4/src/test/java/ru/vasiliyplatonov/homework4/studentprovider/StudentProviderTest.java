package ru.vasiliyplatonov.homework4.studentprovider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.vasiliyplatonov.homework4.service.ioservice.IOService;
import ru.vasiliyplatonov.homework4.service.studentprovider.StudentProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("StudentProviderTest service should have: ")
class StudentProviderTest {

	@Autowired
	private StudentProvider studentProvider;

	@MockBean
	private IOService ioService;


	@BeforeEach
	void setUp() {
		given(ioService.readLine()).willReturn("Name");
	}

	@Test
	void shouldHaveCorrectGetStudent() {
		final var student = studentProvider.getStudent();

		verify(ioService, times(2)).readLine();

		assertThat(student.getFirstName()).isEqualTo("Name");
		assertThat(student.getLastName()).isEqualTo("Name");
	}
}