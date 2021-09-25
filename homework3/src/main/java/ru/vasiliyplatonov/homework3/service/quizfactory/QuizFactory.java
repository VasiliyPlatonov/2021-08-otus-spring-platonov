package ru.vasiliyplatonov.homework3.service.quizfactory;

import ru.vasiliyplatonov.homework3.domain.Quiz;

import java.io.Reader;

public interface QuizFactory {
	Quiz create(Reader inQuiz) throws QuizCreatingException;
}
