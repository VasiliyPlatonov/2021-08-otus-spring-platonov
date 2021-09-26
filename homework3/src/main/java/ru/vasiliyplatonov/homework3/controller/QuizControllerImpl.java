package ru.vasiliyplatonov.homework3.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import ru.vasiliyplatonov.homework3.service.quizfactory.QuizFactory;
import ru.vasiliyplatonov.homework3.service.quizhost.QuizHost;
import ru.vasiliyplatonov.homework3.service.studentprovider.StudentProvider;

import java.io.FileReader;
import java.io.IOException;

import static ru.vasiliyplatonov.homework3.config.AppConfig.REQUIRED_SCORE;

@Controller("quizController")
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
			if (score >= REQUIRED_SCORE) {
				System.out.println(" °˖✧◝(⁰▿⁰)◜✧˖° Congratulations you passed the quiz!!!");
			} else {
				System.out.println(" (ಡ‸ಡ) We are sorry, but you scored too few points...");
			}


		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}




















