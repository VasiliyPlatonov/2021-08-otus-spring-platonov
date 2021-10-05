package ru.vasiliyplatonov.homework3.service.quizfactory;

import ru.vasiliyplatonov.homework3.domain.Quiz;

public interface QuizFactory {
	Quiz create() throws QuizCreatingException;
}
