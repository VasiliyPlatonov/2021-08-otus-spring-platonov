package ru.vasiliyplatonov.homework2.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.vasiliyplatonov.homework2.config.AppConfig;
import ru.vasiliyplatonov.homework2.service.quizfactory.QuizFactory;
import ru.vasiliyplatonov.homework2.service.quizhost.QuizHost;
import ru.vasiliyplatonov.homework2.service.studentprovider.StudentProvider;

import java.io.FileReader;
import java.io.IOException;

@Component("quizController")
public class QuizControllerImpl implements QuizController {

	private final Resource quizFile;
	private final QuizFactory quizFactory;
	private final StudentProvider studentProvider;
	private final QuizHost quizHost;


	public QuizControllerImpl(@Value("${classpath:quiz/quiz1.csv}") Resource quizFile,
	                          QuizFactory quizFactory,
	                          StudentProvider studentProvider,
	                          QuizHost quizHost) {
		this.quizFile = quizFile;
		this.quizFactory = quizFactory;
		this.studentProvider = studentProvider;
		this.quizHost = quizHost;
	}


	@Override
	public void showQuiz() {
		try {
			val quiz = quizFactory.create(new FileReader(quizFile.getFile()));
			System.out.println(quiz);
		} catch (Exception e) {
			System.out.println("The error occurred while creating the quiz: " + e.getMessage());
		}

	}

	@Override
	public void conductQuiz() {
		try {
			val quiz = quizFactory.create(new FileReader(quizFile.getFile()));
			val student = studentProvider.getStudent();
			val score = quizHost.conductQuiz(quiz);
			student.setScore(score);

			System.out.println("\n\nYour score is " + score);
			if (score >= AppConfig.CREDIT_COUNT) {
				System.out.println(" °˖✧◝(⁰▿⁰)◜✧˖° Congratulations you passed the quiz!!!");
			} else {
				System.out.println(" (ಡ‸ಡ) We are sorry, but you scored too few points...");
			}


		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}




















