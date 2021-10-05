package ru.vasiliyplatonov.homework3.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import ru.vasiliyplatonov.homework3.service.i18n.LocalizedMessageSource;
import ru.vasiliyplatonov.homework3.service.quizfactory.QuizFactory;
import ru.vasiliyplatonov.homework3.service.quizhost.QuizHost;
import ru.vasiliyplatonov.homework3.service.studentprovider.StudentProvider;

@RequiredArgsConstructor
public class QuizControllerImpl implements QuizController {

	private final QuizFactory quizFactory;
	private final StudentProvider studentProvider;
	private final QuizHost quizHost;
	private final LocalizedMessageSource msgSource;
	private final int requiredScore;


	@Override
	public void showQuiz() {
		try {
			val quiz = quizFactory.create();
			System.out.println(quiz);
		} catch (Exception e) {
			System.out.println("The error occurred while creating the quiz: " + e.getMessage());
		}

	}

	@Override
	public void conductQuiz() {
		val quiz = quizFactory.create();
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




















