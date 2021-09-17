package ru.vasiliyplatonov.homework2.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import ru.vasiliyplatonov.homework2.service.quizfactory.QuizFactory;

import java.io.FileReader;

@Controller("quizController")
public class QuizControllerImpl implements QuizController {

	private final Resource quizFile;
	private final QuizFactory quizFactory;

	public QuizControllerImpl(@Value("${classpath:quiz/quiz1.csv}") Resource quizFile, QuizFactory quizFactory) {
		this.quizFactory = quizFactory;
		this.quizFile = quizFile;
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
}
