package ru.vasiliyplatonov.homework3.config;

import lombok.val;
import org.apache.commons.csv.CSVFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.vasiliyplatonov.homework3.service.messagesource.LocalizedMessageSource;
import ru.vasiliyplatonov.homework3.service.messagesource.LocalizedMessageSourceImpl;
import ru.vasiliyplatonov.homework3.service.quizhost.QuizHost;
import ru.vasiliyplatonov.homework3.service.quizhost.StdInQuizHost;
import ru.vasiliyplatonov.homework3.service.studentprovider.StdInStudentProvider;
import ru.vasiliyplatonov.homework3.service.studentprovider.StudentProvider;

import java.util.Locale;
import java.util.Scanner;


@Configuration
@ComponentScan({"ru.vasiliyplatonov.homework3"})
public class AppConfig {

	@Value("${quiz.headers}")
	private String[] headers;

	@Value("${application.language-tag}")
	private String languageTag;


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
}
