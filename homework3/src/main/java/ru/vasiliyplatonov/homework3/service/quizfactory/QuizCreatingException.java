package ru.vasiliyplatonov.homework3.service.quizfactory;

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
