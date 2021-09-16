package ru.vasiliyplatonov.homework2.service.quizfactory;

import org.apache.commons.csv.CSVFormat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("CsvQuizFactory service should have: ")
class CsvQuizFactoryTest {

    private CsvQuizFactory csvQuizFactory;

    private static Reader quizIn;
    private static CSVFormat csvFormat;
    private static final String[] HEADERS = {"question", "a", "b", "c", "d", "answer"};

    @BeforeAll
    protected static void beforeAll() throws IOException {
        final File quizFile = new ClassPathResource("quiz/testQuiz1.csv").getFile();
        csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withHeader(HEADERS);
        quizIn = new FileReader(quizFile);
    }

    @BeforeEach
    void setUp() {
        csvQuizFactory = new CsvQuizFactory(csvFormat);
    }


    @Test
    @DisplayName("not null quiz")
    void shouldHaveCorrectCreateQuiz() throws QuizCreatingException {
        final var quiz = csvQuizFactory.create(quizIn);
        assertThat(quiz).isNotNull();
    }

    @Test
    @DisplayName("correct questions")
    void shouldHaveCorrectQuizQuestion() throws QuizCreatingException {
        final var quiz = csvQuizFactory.create(quizIn);
        assertThat(quiz.getQuestions())
                .isNotNull()
                .isNotEmpty()
                .hasSize(2);
    }


}