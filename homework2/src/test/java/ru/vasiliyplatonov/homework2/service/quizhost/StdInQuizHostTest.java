package ru.vasiliyplatonov.homework2.service.quizhost;

import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vasiliyplatonov.homework2.domain.Answer;
import ru.vasiliyplatonov.homework2.domain.Question;
import ru.vasiliyplatonov.homework2.domain.Quiz;
import ru.vasiliyplatonov.homework2.service.quizfactory.CsvQuizFactory;

import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("StdInQuizHost service should have: ")
@ExtendWith(MockitoExtension.class)
class StdInQuizHostTest {

	private StdInQuizHost quizHost;

	@Mock
	private Scanner scanner;

	@Mock
	private CsvQuizFactory csvQuizFactory;

	private static Quiz quiz;

	@BeforeAll
	static void beforeAll() {
		val answers = List.of(
				new Answer("a", "this is 'a'", false),
				new Answer("b", "this is 'b'", false),
				new Answer("c", "this is 'c'", true),
				new Answer("d", "this is 'd'", false)
		);

		quiz = new Quiz(List.of(
				new Question("First question", answers),
				new Question("Second question", answers)
		));
	}

	@BeforeEach
	void setUp() {
		given(scanner.nextLine())
				.willReturn("c")
				.willReturn("a");
		quizHost = new StdInQuizHost(scanner);
	}

	@Test
	@DisplayName("correct conducts quiz")
	void conductQuizTest() {
		final var score = quizHost.conductQuiz(quiz);

		assertThat(score).isEqualTo(1);
	}
}