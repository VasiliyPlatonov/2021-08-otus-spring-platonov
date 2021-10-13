package ru.vasiliyplatonov.homework4.service.quizfileprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service("quizProvider")
public class ClassPathLocalizedQuizFileProvider implements QuizFileProvider {

	private final String languageTag;

	public ClassPathLocalizedQuizFileProvider(@Value("${application.language-tag:}") String languageTag) {
		this.languageTag = languageTag;
	}


	@Override
	public File getQuizFile() {
		var fileName = "quiz/quiz1.csv";

		if ("ru-RU".equals(languageTag)) {
			fileName = "quiz/quiz1_ru.csv";
		}

		try {
			return new ClassPathResource(fileName).getFile();
		} catch (IOException e) {
			throw new QuizFileProvidingException(String.format("Failed to provide quiz file with name '%s'", fileName));
		}
	}
}
