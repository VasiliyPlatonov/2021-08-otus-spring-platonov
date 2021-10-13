package ru.vasiliyplatonov.homework4.service.quizhost;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework4.domain.Answer;
import ru.vasiliyplatonov.homework4.domain.Question;
import ru.vasiliyplatonov.homework4.domain.Quiz;
import ru.vasiliyplatonov.homework4.service.ioservice.IOService;
import ru.vasiliyplatonov.homework4.service.messagesource.LocalizedMessageSource;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class QuizHostImpl implements QuizHost {

	private final IOService ioService;
	private final LocalizedMessageSource msgSource;


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
		ioService.out(msgSource.getMessage("enter-your-answer") + ": ");
		val userAnswer = ioService.readLine();

		val countOfCorrect = (int) (possibleAnswers.stream()
				.filter(answer -> answer.getName().equalsIgnoreCase(userAnswer) &&
						answer.isCorrect())
				.count());

		return countOfCorrect;
	}

	private void showAnswers(Collection<Answer> possibleAnswers)
	{
		possibleAnswers.forEach(answer -> ioService.outLine(answer.toString()));
	}

	private void showQuestion(Question question)
	{
		ioService.outLine(System.lineSeparator() + question.getTitle());
	}
}
