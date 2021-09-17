package ru.vasiliyplatonov.homework2.config;

import org.apache.commons.csv.CSVFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({"ru.vasiliyplatonov.homework2"})
public class AppConfig {

	public final static String[] HEADERS = {"question", "a", "b", "c", "d", "answer"};

	@Bean
	CSVFormat csvFormat() {
		return CSVFormat.DEFAULT
				.withFirstRecordAsHeader()
				.withHeader(HEADERS);
	}
}
