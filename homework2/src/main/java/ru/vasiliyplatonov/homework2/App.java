package ru.vasiliyplatonov.homework2;

import lombok.val;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vasiliyplatonov.homework2.config.AppConfig;
import ru.vasiliyplatonov.homework2.controller.QuizController;


public class App {
	public static void main(String[] args) {

		val applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		val quizController = applicationContext.getBean("quizController", QuizController.class);

		quizController.showQuiz();
	}
}
