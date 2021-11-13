package ru.vasiliyplatonov.homework5.dao;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.vasiliyplatonov.homework5.domain.Author;
import ru.vasiliyplatonov.homework5.domain.Book;
import ru.vasiliyplatonov.homework5.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
@DisplayName("Book JDBC Dao")
@Import(BookJdbcDao.class)
class BookJdbcDaoTest {

	@Autowired
	private BookJdbcDao bookDao;


	@Test
	@DisplayName("should return expected book by its id")
	void shouldReturnExpectedBookById() {
		val expectedAuthor = new Author(1, "Stephen", "King");
		val expectedGenre = new Genre(1, "thriller");
		val expectedBook = new Book(1, "The Green Mile", expectedAuthor, expectedGenre);

		val actualBook = bookDao.getById(expectedBook.getId());

		assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
	}

	@Test
	@DisplayName("should return expected books by their genre")
	void shouldReturnExpectedBooksByGenre() {
		val expectedGenre = new Genre(4, "poem");

		val expectedBooks = List.of(
				new Book(4, "Demon", new Author(2, "Mikhail", "Lermontov"), expectedGenre)
		);

		val actualBooks = bookDao.getByGenre(expectedGenre);

		assertThat(actualBooks)
				.usingFieldByFieldElementComparator()
				.isEqualTo(expectedBooks);
	}

	@Test
	@DisplayName("should return expected books by their author")
	void shouldReturnExpectedBooksByAuthor() {
		val expectedAuthor = new Author(2, "Mikhail", "Lermontov");


		val expectedBooks = List.of(
				new Book(3, "A Hero of Our Time", expectedAuthor, new Genre(3, "psychological fiction")),
				new Book(4, "Demon", expectedAuthor, new Genre(4, "poem"))
		);

		val actualBooks = bookDao.getByAuthor(expectedAuthor);

		assertThat(actualBooks)
				.usingFieldByFieldElementComparator()
				.isEqualTo(expectedBooks);
	}

	@Test
	@DisplayName("should return expected books by their author")
	void shouldReturnExpectedBooksByGenreName() {
		val expectedGenre = new Genre(4, "poem");

		val expectedBooks = List.of(
				new Book(4, "Demon", new Author(2, "Mikhail", "Lermontov"), expectedGenre)
		);

		val actualBooks = bookDao.getByGenreName(expectedGenre.getName());

		assertThat(actualBooks)
				.usingFieldByFieldElementComparator()
				.isEqualTo(expectedBooks);
	}

	@Test
	@DisplayName("should return expected books by their title")
	void shouldReturnExpectedBooksById() {
		val expectedTitle = "Demon";
		val expectedAuthor = new Author(2, "Mikhail", "Lermontov");
		val expectedGenre = new Genre(4, "poem");

		val expectedBooks = List.of(
				new Book(4, "Demon", expectedAuthor, expectedGenre)
		);

		val actualBooks = bookDao.getByTitle(expectedTitle);

		assertThat(actualBooks)
				.usingFieldByFieldElementComparator()
				.isEqualTo(expectedBooks);
	}


	@Test
	@DisplayName("should return all expected books")
	void shouldReturnAllExpectedBook() {

		val king = new Author(1, "Stephen", "King");
		val lermontov = new Author(2, "Mikhail", "Lermontov");

		val thriller = new Genre(1, "thriller");
		val drama = new Genre(2, "drama");
		val psyFiction = new Genre(3, "psychological fiction");
		val poem = new Genre(4, "poem");

		val expectedBooks = List.of(
				new Book(1, "The Green Mile", king, thriller),
				new Book(2, "Rita Hayworth and Shawshank Redemption", king, drama),
				new Book(3, "A Hero of Our Time", lermontov, psyFiction),
				new Book(4, "Demon", lermontov, poem)
		);

		val actualBooks = bookDao.getAll();

		assertThat(actualBooks)
				.usingFieldByFieldElementComparator()
				.isEqualTo(expectedBooks);
	}

	@Test
	@DisplayName("should correct add book in DB")
	void shouldCorrectAddBook() {
		val king = new Author(1, "Stephen", "King");
		val thriller = new Genre(1, "thriller");
		val expectedBook = new Book(0, "It", king, thriller);

		val actualId = bookDao.add(expectedBook);
		val actualBook = bookDao.getById(actualId);

		assertThat(actualBook)
				.usingRecursiveComparison()
				.ignoringFields("id")
				.isEqualTo(expectedBook);
	}

	@Test
	@DisplayName("should correct delete book from DB by id")
	void shouldCorrectDeleteBookById() {
		val existingBookId = 1;

		bookDao.deleteById(existingBookId);

		assertThatThrownBy(() -> bookDao.getById(existingBookId))
				.isInstanceOf(EmptyResultDataAccessException.class);
	}

	@Test
	@DisplayName("should correct delete book from DB by title")
	void shouldCorrectDeleteBookByTitle() {
		val existingBookTitle = "Demon";
		bookDao.deleteByTitle(existingBookTitle);

		assertThat(bookDao.getByTitle(existingBookTitle)).isEmpty();
	}

	@Test
	@DisplayName("should correct delete list of books")
	void shouldCorrectDeleteListOfBooks() {
		val existingBooks = bookDao.getAll();
		bookDao.delete(existingBooks);

		assertThat(bookDao.getAll()).isEmpty();
	}

	@Test
	@DisplayName("should correct delete books by author")
	void shouldCorrectDeleteBooksByAuthor() {
		val existingAuthor = new Author(1, "Stephen", "King");

		assertThat(bookDao.getByAuthor(existingAuthor)).isNotEmpty();
		bookDao.deleteByAuthor(existingAuthor);
		assertThat(bookDao.getByAuthor(existingAuthor)).isEmpty();
	}


	@Test
	@DisplayName("should correct update book")
	void shouldCorrectUpdateBook() {
		val existingBookId = 1;
		val existingBook = bookDao.getById(existingBookId);

		val expectedBook = new Book(
				existingBook.getId(),
				"newTitle",
				existingBook.getAuthor(),
				existingBook.getGenre()
		);

		bookDao.update(expectedBook);

		val actualBook = bookDao.getById(existingBookId);

		assertThat(actualBook)
				.usingRecursiveComparison()
				.isNotEqualTo(existingBook)
				.isEqualTo(expectedBook);
	}
}