package ru.vasiliyplatonov.homework3.config;

import org.apache.commons.csv.CSVFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.vasiliyplatonov.homework3.service.quizhost.QuizHost;
import ru.vasiliyplatonov.homework3.service.quizhost.StdInQuizHost;
import ru.vasiliyplatonov.homework3.service.studentprovider.StdInStudentProvider;
import ru.vasiliyplatonov.homework3.service.studentprovider.StudentProvider;

import java.util.Scanner;


@Configuration
@ComponentScan({"ru.vasiliyplatonov.homework3"})
public class AppConfig {

	@Value("${quiz.headers}")
	public String[] HEADERS;

	@Value("${quiz.credit-count: 4}")  // default 4
	public static int REQUIRED_SCORE;


	@Bean
	CSVFormat csvFormat() {
		return CSVFormat.DEFAULT
				.withFirstRecordAsHeader()
				.withHeader(HEADERS);
	}


	@Bean
	public StudentProvider studentProvider() {
		return new StdInStudentProvider(new Scanner(System.in));
	}

	@Bean
	public QuizHost quizHost() {
		return new StdInQuizHost(new Scanner(System.in));
	}
}
