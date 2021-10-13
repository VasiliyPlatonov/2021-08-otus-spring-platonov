package ru.vasiliyplatonov.homework4.quizfactory;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import ru.vasiliyplatonov.homework4.service.quizfactory.CsvQuizFactory;
import ru.vasiliyplatonov.homework4.service.quizfactory.QuizCreatingException;

import java.io.File;
import java.io.IOException;

@SpringBootTest
@DisplayName("CsvQuizFactory service should have: ")
class CsvQuizFactoryTest {

    @Autowired
    private CsvQuizFactory csvQuizFactory;

    private static File quizFile;

    @BeforeAll
    protected static void beforeAll() throws IOException {
        quizFile = new ClassPathResource("quiz/testQuiz1.csv").getFile();
    }


    @Test
    @DisplayName("not null quiz")
    void shouldHaveCorrectCreateQuiz() throws QuizCreatingException {
        val quiz = csvQuizFactory.create(quizFile);
        Assertions.assertThat(quiz).isNotNull();
    }

    @Test
    @DisplayName("correct questions")
    void shouldHaveCorrectQuizQuestion() throws QuizCreatingException {
        final var quiz = csvQuizFactory.create(quizFile);
        Assertions.assertThat(quiz.getQuestions())
                .isNotNull()
                .isNotEmpty()
                .hasSize(2);
    }


}