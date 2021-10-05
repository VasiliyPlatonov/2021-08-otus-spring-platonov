package ru.vasiliyplatonov.homework3.config;

import lombok.val;
import org.apache.commons.csv.CSVFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import ru.vasiliyplatonov.homework3.controller.QuizController;
import ru.vasiliyplatonov.homework3.controller.QuizControllerImpl;
import ru.vasiliyplatonov.homework3.service.i18n.LocalizedMessageSource;
import ru.vasiliyplatonov.homework3.service.i18n.LocalizedMessageSourceImpl;
import ru.vasiliyplatonov.homework3.service.quizfactory.CsvQuizFactory;
import ru.vasiliyplatonov.homework3.service.quizfactory.QuizFactory;
import ru.vasiliyplatonov.homework3.service.quizhost.QuizHost;
import ru.vasiliyplatonov.homework3.service.quizhost.StdInQuizHost;
import ru.vasiliyplatonov.homework3.service.studentprovider.StdInStudentProvider;
import ru.vasiliyplatonov.homework3.service.studentprovider.StudentProvider;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;


@Configuration
@ComponentScan({"ru.vasiliyplatonov.homework3"})
public class AppConfig {

	@Value("${quiz.headers}")
	private String[] headers;

	@Value("${application.language-tag}")
	private String languageTag;

	@Value("${quiz.required-score:4}")
	private int requiredScore;


	@Bean
	public ResourceBundleMessageSource messageSource() {
		val source = new ResourceBundleMessageSource();
		source.setBasenames("i18n/messages");
		source.setDefaultEncoding("UTF-8");
		source.setUseCodeAsDefaultMessage(true);

		return source;
	}

	@Bean
	public LocalizedMessageSource localizedMessageSource() {
		val locale = Locale.forLanguageTag(languageTag);

		return new LocalizedMessageSourceImpl(locale, messageSource());
	}

	@Bean
	public StudentProvider studentProvider() {
		return new StdInStudentProvider(new Scanner(System.in), localizedMessageSource());
	}

	@Bean
	public QuizHost quizHost() {
		return new StdInQuizHost(new Scanner(System.in), localizedMessageSource());
	}

	@Bean
	CSVFormat csvFormat() {
		return CSVFormat.DEFAULT
				.withFirstRecordAsHeader()
				.withHeader(headers);
	}

	@Bean
	public QuizFactory quizFactory() throws IOException {
		return new CsvQuizFactory(csvFormat(), getQuizFile());
	}

	@Bean
	public QuizController quizController() throws IOException {
		val quizFactory = quizFactory();
		val studentProvider = studentProvider();
		val quizHost = quizHost();
		val localizedMsgSource = localizedMessageSource();

		return new QuizControllerImpl(quizFactory, studentProvider, quizHost, localizedMsgSource, requiredScore);
	}

	private File getQuizFile() throws IOException {
		if ("ru-RU".equals(languageTag)) {
			return new ClassPathResource("quiz/quiz1_ru.csv").getFile();
		}
		return new ClassPathResource("quiz/quiz1.csv").getFile();
	}
}
