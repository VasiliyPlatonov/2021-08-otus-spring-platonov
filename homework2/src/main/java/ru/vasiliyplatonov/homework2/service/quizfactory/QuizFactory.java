package ru.vasiliyplatonov.homework2.service.quizfactory;

import ru.vasiliyplatonov.homework2.domain.Quiz;

import java.io.Reader;

public interface QuizFactory {
    Quiz create(Reader inQuiz) throws QuizCreatingException;
}
