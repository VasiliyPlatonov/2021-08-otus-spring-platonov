package ru.vasiliyplatonov.homework4.service.quizfactory;

import ru.vasiliyplatonov.homework4.domain.Quiz;

import java.io.File;

public interface QuizFactory {
	Quiz create(File quizFile) throws QuizCreatingException;
}
