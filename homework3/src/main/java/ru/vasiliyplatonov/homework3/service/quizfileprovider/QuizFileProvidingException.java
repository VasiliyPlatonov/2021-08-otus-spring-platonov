package ru.vasiliyplatonov.homework3.service.quizfileprovider;

public class QuizFileProvidingException extends RuntimeException {
	public QuizFileProvidingException() {
		super();
	}

	public QuizFileProvidingException(String message) {
		super(message);
	}

	public QuizFileProvidingException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuizFileProvidingException(Throwable cause) {
		super(cause);
	}
}
