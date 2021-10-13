package ru.vasiliyplatonov.homework3;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.vasiliyplatonov.homework3.controller.QuizController;


@SpringBootApplication
public class App {
	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(App.class, args);
		val quizController = ctx.getBean("quizController", QuizController.class);

		quizController.conductQuiz();

	}
}
