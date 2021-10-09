package ru.vasiliyplatonov.homework3.service.quizfactory;

import ru.vasiliyplatonov.homework3.domain.Quiz;

import java.io.File;

public interface QuizFactory {
	Quiz create(File quizFile) throws QuizCreatingException;
}
