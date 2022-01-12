package ru.vasiliyplatonov.homework5.service;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.vasiliyplatonov.homework5.config.DaoMocksConfig;
import ru.vasiliyplatonov.homework5.dao.AuthorDao;
import ru.vasiliyplatonov.homework5.dao.BookDao;
import ru.vasiliyplatonov.homework5.dao.GenreDao;
import ru.vasiliyplatonov.homework5.domain.Author;
import ru.vasiliyplatonov.homework5.domain.Book;
import ru.vasiliyplatonov.homework5.domain.Genre;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@DisplayName("BookServiceImpl tests: ")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoMocksConfig.class, BookServiceImpl.class})
class BookServiceImplTest {

	@Autowired
	private BookServiceImpl bookService;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private AuthorDao authorDao;

	@Autowired
	private GenreDao genreDao;

	private static final Genre EXPECTED_GENRE = new Genre(1, "thriller");
	private static final Author EXPECTED_AUTHOR = new Author(1, "Stephen", "King");
	private static final Book EXPECTED_BOOK = new Book(1, "The Green Mile", EXPECTED_AUTHOR, EXPECTED_GENRE);


	@Test
	@DisplayName("should correct get book by id")
	void shouldCorrectGetBookById() {
		given(bookDao.getById(1)).willReturn(EXPECTED_BOOK);
		val actualBook = bookService.getById(EXPECTED_BOOK.getId());
		assertThat(actualBook).usingRecursiveComparison().isEqualTo(EXPECTED_BOOK);
	}

	@Test
	@DisplayName("should return null if book not exists")
	void shouldReturnNullIfBookNotExists() {
		val notExistsId = 123L;
		given(bookDao.getById(notExistsId))
				.willThrow(EmptyResultDataAccessException.class);

		assertThat(bookService.getById(notExistsId)).isNull();
	}

	@Test
	@DisplayName("should correct add book if author and genre exist")
	void shouldCorrectAddBookIfAuthorAndGenreExist() {
		given(authorDao.existsByFirstNameAndLastName(EXPECTED_AUTHOR.getFirstName(), EXPECTED_AUTHOR.getLastName()))
				.willReturn(true);
		given(authorDao.getByFirstNameAndLastName(any(), any())).willReturn(EXPECTED_AUTHOR);
		given(genreDao.existsByName(any())).willReturn(true);
		given(genreDao.getByName(any())).willReturn(EXPECTED_GENRE);
		given(bookDao.add(any(Book.class))).willReturn(EXPECTED_BOOK.getId());

		val actualBook = bookService.add(new Book(0, EXPECTED_BOOK.getTitle(), EXPECTED_AUTHOR, EXPECTED_GENRE));
		assertThat(actualBook)
				.usingRecursiveComparison()
				.isEqualTo(EXPECTED_BOOK);

		verify(authorDao, never()).add(any(Author.class));
		verify(genreDao, never()).add(any(Genre.class));
	}

	@Test
	@DisplayName("should correct add book if author and genre not exist")
	void shouldCorrectAddBookIfAuthorAndGenreNotExist() {
		given(authorDao.existsByFirstNameAndLastName(EXPECTED_AUTHOR.getFirstName(), EXPECTED_AUTHOR.getLastName()))
				.willReturn(false);
		given(authorDao.getByFirstNameAndLastName(any(), any())).willReturn(EXPECTED_AUTHOR);

		given(genreDao.existsByName(EXPECTED_GENRE.getName())).willReturn(false);
		given(genreDao.getByName(any())).willReturn(EXPECTED_GENRE);

		given(bookDao.add(any(Book.class))).willReturn(EXPECTED_BOOK.getId());

		val actualBook = bookService.add(new Book(0, EXPECTED_BOOK.getTitle(), EXPECTED_AUTHOR, EXPECTED_GENRE));

		assertThat(actualBook).usingRecursiveComparison().isEqualTo(EXPECTED_BOOK);

		verify(authorDao, times(1)).add(EXPECTED_AUTHOR);
		verify(genreDao, times(1)).add(any());
	}

}