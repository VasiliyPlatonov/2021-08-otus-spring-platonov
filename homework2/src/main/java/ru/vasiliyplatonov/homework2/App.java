package ru.vasiliyplatonov.homework2;

import lombok.val;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.vasiliyplatonov.homework2.controller.QuizController;

public class App {
    public static void main(String[] args) throws Exception {

        val applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        val quizController = applicationContext.getBean("quizController", QuizController.class);

        quizController.showQuiz();
    }
}
