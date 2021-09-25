package ru.vasiliyplatonov.homework3.config;

import org.apache.commons.csv.CSVFormat;
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

	public final static String[] HEADERS = {"question", "a", "b", "c", "d", "answer"};
	public final static int CREDIT_COUNT = 3;


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
