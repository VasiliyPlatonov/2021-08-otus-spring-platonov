package ru.vasiliyplatonov.homework3.service.quizhost;

import lombok.RequiredArgsConstructor;
import lombok.val;
import ru.vasiliyplatonov.homework3.domain.Answer;
import ru.vasiliyplatonov.homework3.domain.Question;
import ru.vasiliyplatonov.homework3.domain.Quiz;

import java.util.Collection;
import java.util.Scanner;

@RequiredArgsConstructor
public class StdInQuizHost implements QuizHost {

	private final Scanner scanner;


	@Override
	public int conductQuiz(Quiz quiz) {
		val questions = quiz.getQuestions();
		return questions.stream()
				.mapToInt(
						question -> {
							showQuestion(question);
							showAnswers(question.getPossibleAnswers());
							val score = handleUserAnswer(question.getPossibleAnswers());

							return score;
						})
				.sum();
	}

	private int handleUserAnswer(Collection<Answer> possibleAnswers) {
		System.out.print("Enter your answer:");
		val userAnswer = scanner.nextLine();

		val countOfCorrect = (int) (possibleAnswers.stream()
				.filter(answer -> answer.getName().equalsIgnoreCase(userAnswer) &&
						answer.isCorrect())
				.count());

		return countOfCorrect;
	}

	private void showAnswers(Collection<Answer> possibleAnswers) {
		possibleAnswers.forEach(System.out::println);
	}

	private void showQuestion(Question question) {
		System.out.println("\n" + question.getTitle());
	}
}
