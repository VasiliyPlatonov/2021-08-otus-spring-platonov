package ru.vasiliyplatonov.homework1.service.quizfactory;

public class QuizCreatingException extends Exception {
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
