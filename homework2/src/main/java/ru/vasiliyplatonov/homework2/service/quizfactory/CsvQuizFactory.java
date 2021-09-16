package ru.vasiliyplatonov.homework2.service.quizfactory;

import lombok.val;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.vasiliyplatonov.homework2.domain.Answer;
import ru.vasiliyplatonov.homework2.domain.Question;
import ru.vasiliyplatonov.homework2.domain.Quiz;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CsvQuizFactory implements QuizFactory {

    private final CSVFormat csvFormat;

    public CsvQuizFactory(CSVFormat csvFormat) {
        this.csvFormat = csvFormat;
    }

    @Override
    public Quiz create(Reader inQuiz) {
        try {
            val csvQuizRecords = csvFormat.parse(inQuiz);
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
        String question = csvRecord.get("question");
        String answer = csvRecord.get("answer");

        val a = new Answer(csvRecord.get("a"), answer.equals("a"));
        val b = new Answer(csvRecord.get("b"), answer.equals("b"));
        val c = new Answer(csvRecord.get("c"), answer.equals("c"));
        val d = new Answer(csvRecord.get("d"), answer.equals("d"));

        return new Question(question, Arrays.asList(a, b, c, d));
    }
}
