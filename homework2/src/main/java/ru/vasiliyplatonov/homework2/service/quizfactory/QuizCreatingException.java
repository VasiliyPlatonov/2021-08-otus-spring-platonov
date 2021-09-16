package ru.vasiliyplatonov.homework2.service.quizfactory;

public class QuizCreatingException extends RuntimeException {
    public QuizCreatingException() {
        super();
    }

    public QuizCreatingException(String message) {
        super(message);
    }

    public QuizCreatingException(String message, Throwable cause) {
        super(message, cause);
    }
}
