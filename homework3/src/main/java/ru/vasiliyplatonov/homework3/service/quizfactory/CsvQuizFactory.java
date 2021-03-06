package ru.vasiliyplatonov.homework3.service.quizfactory;

import lombok.val;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework3.domain.Answer;
import ru.vasiliyplatonov.homework3.domain.Question;
import ru.vasiliyplatonov.homework3.domain.Quiz;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service("quizFactory")
public class CsvQuizFactory implements QuizFactory {

	private final CSVFormat csvFormat;

	private static final String[] HEADERS = {"question", "a", "b", "c", "d", "answer"};

	public CsvQuizFactory(@Value("#{T(org.apache.commons.csv.CSVFormat).DEFAULT}") CSVFormat csvFormat) {
		this.csvFormat = csvFormat
				.withFirstRecordAsHeader()
				.withHeader(HEADERS);
	}

	@Override
	public Quiz create(File quizFile) {
		try {
			val csvQuizRecords = csvFormat.parse(new FileReader(quizFile));
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
