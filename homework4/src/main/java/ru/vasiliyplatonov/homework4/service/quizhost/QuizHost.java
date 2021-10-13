package ru.vasiliyplatonov.homework4.service.quizhost;


import ru.vasiliyplatonov.homework4.domain.Quiz;

public interface QuizHost {

	/**
	 * Conducts the quiz
	 *
	 * @return result (score) of quiz
	 */
	int conductQuiz(Quiz quiz);
}
