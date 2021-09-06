package ru.vasiliyplatonov.homework1.service.quizfactory;

import ru.vasiliyplatonov.homework1.domain.Quiz;

import java.io.Reader;

public interface QuizFactory {
    Quiz create(Reader inQuiz) throws QuizCreatingException;
}
