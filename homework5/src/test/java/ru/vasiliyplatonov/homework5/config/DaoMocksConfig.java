package ru.vasiliyplatonov.homework5.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vasiliyplatonov.homework5.dao.AuthorDao;
import ru.vasiliyplatonov.homework5.dao.BookDao;
import ru.vasiliyplatonov.homework5.dao.BookJdbcDao;
import ru.vasiliyplatonov.homework5.dao.GenreDao;

@Configuration
public class DaoMocksConfig {

	@Bean
	BookDao bookDao() {
		return Mockito.mock(BookJdbcDao.class);
	}

	@Bean
	AuthorDao authorDao() {
		return Mockito.mock(AuthorDao.class);

	}

	@Bean
	GenreDao genreDao() {
		return Mockito.mock(GenreDao.class);
	}

}
