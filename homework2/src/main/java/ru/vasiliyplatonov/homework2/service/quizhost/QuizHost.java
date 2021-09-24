package ru.vasiliyplatonov.homework2.service.quizhost;

import ru.vasiliyplatonov.homework2.domain.Quiz;

public interface QuizHost {

	/**
	 * Conducts the quiz
	 *
	 * @return result (score) of quiz
	 */
	int conductQuiz(Quiz quiz);
}
