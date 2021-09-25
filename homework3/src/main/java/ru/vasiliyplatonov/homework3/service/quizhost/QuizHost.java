package ru.vasiliyplatonov.homework3.service.quizhost;


import ru.vasiliyplatonov.homework3.domain.Quiz;

public interface QuizHost {

	/**
	 * Conducts the quiz
	 *
	 * @return result (score) of quiz
	 */
	int conductQuiz(Quiz quiz);
}
