package ru.vasiliyplatonov.homework3.quizhost;

import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.vasiliyplatonov.homework3.domain.Answer;
import ru.vasiliyplatonov.homework3.domain.Question;
import ru.vasiliyplatonov.homework3.domain.Quiz;
import ru.vasiliyplatonov.homework3.service.ioservice.IOService;
import ru.vasiliyplatonov.homework3.service.quizhost.QuizHost;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("QuizHost service should have: ")
class QuizHostTest {

    @MockBean
    private IOService ioService;

    @Autowired
    private QuizHost quizHost;

    private static Quiz testQuiz;

    @BeforeAll
    static void beforeAll() {
        val answers = List.of(
                new Answer("a", "this is 'a'", false),
                new Answer("b", "this is 'b'", false),
                new Answer("c", "this is 'c'", true),
                new Answer("d", "this is 'd'", false)
        );

        testQuiz = new Quiz(List.of(
                new Question("First question", answers),
                new Question("Second question", answers)
        ));
    }

    @BeforeEach
    void setUp() {
        given(ioService.readLine())
                .willReturn("c")
                .willReturn("a");
    }

    @Test
    @DisplayName("correct conducts quiz")
    void conductQuizTest() {

        val score = quizHost.conductQuiz(testQuiz);
        assertThat(score).isEqualTo(1);

        verify(ioService, times(2)).readLine();
    }
}