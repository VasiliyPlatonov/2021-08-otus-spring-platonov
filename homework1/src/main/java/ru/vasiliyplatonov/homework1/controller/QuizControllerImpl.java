package ru.vasiliyplatonov.homework1.controller;

import lombok.val;
import org.springframework.core.io.Resource;
import ru.vasiliyplatonov.homework1.service.quizfactory.QuizCreatingException;
import ru.vasiliyplatonov.homework1.service.quizfactory.QuizFactory;

import java.io.FileReader;
import java.io.IOException;

public class QuizControllerImpl implements QuizController{
    private final QuizFactory quizFactory;
    public final Resource quizFile;

    public QuizControllerImpl(Resource quizFile, QuizFactory quizFactory) {
        this.quizFactory = quizFactory;
        this.quizFile = quizFile;
    }

    public void showQuiz() throws IOException, QuizCreatingException {
        val quiz = quizFactory.create(new FileReader(quizFile.getFile()));
        System.out.println(quiz);
    }
}
