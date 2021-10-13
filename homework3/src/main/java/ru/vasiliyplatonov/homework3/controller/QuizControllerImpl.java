package ru.vasiliyplatonov.homework3.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.vasiliyplatonov.homework3.service.messagesource.LocalizedMessageSource;
import ru.vasiliyplatonov.homework3.service.quizfactory.QuizFactory;
import ru.vasiliyplatonov.homework3.service.quizfileprovider.QuizFileProvider;
import ru.vasiliyplatonov.homework3.service.quizhost.QuizHost;
import ru.vasiliyplatonov.homework3.service.studentprovider.StudentProvider;

@Component("quizController")
public class QuizControllerImpl implements QuizController {

	private final QuizFactory quizFactory;
	private final StudentProvider studentProvider;
	private final QuizFileProvider quizFileProvider;
	private final QuizHost quizHost;
	private final LocalizedMessageSource msgSource;
	private final int requiredScore;

	public QuizControllerImpl(QuizFactory quizFactory,
							  StudentProvider studentProvider,
							  QuizFileProvider quizFileProvider,
							  QuizHost quizHost,
							  LocalizedMessageSource msgSource,
							  @Value("${quiz.required-score}") int requiredScore) {
		this.quizFactory = quizFactory;
		this.studentProvider = studentProvider;
		this.quizFileProvider = quizFileProvider;
		this.quizHost = quizHost;
		this.msgSource = msgSource;
		this.requiredScore = requiredScore;
	}

	@Override
	public void showQuiz() {
		try {
			val quizFile = quizFileProvider.getQuizFile();
			val quiz = quizFactory.create(quizFile);
			System.out.println(quiz);
		} catch (Exception e) {
			System.out.println("The error occurred while creating the quiz: " + e.getMessage());
		}

	}

	@Override
	public void conductQuiz() {
		val quizFile = quizFileProvider.getQuizFile();
		val quiz = quizFactory.create(quizFile);
		val student = studentProvider.getStudent();
		val score = quizHost.conductQuiz(quiz);

		System.out.print("\n\n" + msgSource.getMessage("your-score-is") + " " + score + "\n");
		if (score >= requiredScore) {
			System.out.println(" °˖✧◝(⁰▿⁰)◜✧˖° " + msgSource.getMessage("congratulations-you-pass-the-quiz") + "!!!");
		} else {
			System.out.println(" (ಡ‸ಡ) " + msgSource.getMessage("we-are-sorry-you-scored-too-few-points") + "...");
		}
	}
}




















