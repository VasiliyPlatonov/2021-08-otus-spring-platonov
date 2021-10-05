package ru.vasiliyplatonov.homework3.service.quizfactory;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.vasiliyplatonov.homework3.domain.Answer;
import ru.vasiliyplatonov.homework3.domain.Question;
import ru.vasiliyplatonov.homework3.domain.Quiz;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CsvQuizFactory implements QuizFactory {

	private final CSVFormat csvFormat;
	private final File quiz;

	@Override
	public Quiz create() {
		try {
			val csvQuizRecords = csvFormat.parse(new FileReader(quiz));
			val questions =
					csvQuizRecords.getRecords().stream()
							.map(CsvQuizFactory::toQuestion)
							.collect(Collectors.toList());

			return new Quiz(questions);

		} catch (IOException e) {
			throw new QuizCreatingException("Failed quiz creation", e);
		}
	}

	private static Question toQuestion(CSVRecord csvRecord) {
		val question = csvRecord.get("question");
		val correctAnswer = csvRecord.get("answer");
		val possibleAnswers = new ArrayList<Answer>();


		if (!csvRecord.get("a").isBlank()) {
			possibleAnswers.add(new Answer("a", csvRecord.get("a"), correctAnswer.equals("a")));
		}
		if (!csvRecord.get("b").isBlank()) {
			possibleAnswers.add(new Answer("b", csvRecord.get("b"), correctAnswer.equals("b")));
		}
		if (!csvRecord.get("c").isBlank()) {
			possibleAnswers.add(new Answer("c", csvRecord.get("c"), correctAnswer.equals("c")));
		}
		if (!csvRecord.get("d").isBlank()) {
			possibleAnswers.add(new Answer("d", csvRecord.get("d"), correctAnswer.equals("d")));
		}

		return new Question(question, possibleAnswers);
	}
}
