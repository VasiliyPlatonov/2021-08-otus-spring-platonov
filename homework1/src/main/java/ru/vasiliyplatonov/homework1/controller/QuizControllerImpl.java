package ru.vasiliyplatonov.homework1.controller;

import lombok.val;
import org.springframework.core.io.Resource;
import ru.vasiliyplatonov.homework1.service.quizfactory.QuizFactory;

import java.io.FileReader;

public class QuizControllerImpl implements QuizController {
    private final QuizFactory quizFactory;
    public final Resource quizFile;

    public QuizControllerImpl(Resource quizFile, QuizFactory quizFactory) {
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
