package ru.vasiliyplatonov.homework5.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.vasiliyplatonov.homework5.dao.AuthorDao;
import ru.vasiliyplatonov.homework5.dao.BookDao;
import ru.vasiliyplatonov.homework5.dao.GenreDao;
import ru.vasiliyplatonov.homework5.domain.Author;
import ru.vasiliyplatonov.homework5.domain.Book;
import ru.vasiliyplatonov.homework5.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookDao bookDao;
	private final AuthorDao authorDao;
	private final GenreDao genreDao;

	@Override
	public Book add(Book book) {
		var author = book.getAuthor();
		var genre = book.getGenre();

		if (!authorDao.existsByFirstNameAndLastName(author.getFirstName(), author.getLastName())) {
			authorDao.add(author);
		}

		if (!genreDao.existsByName(genre.getName())) {
			genreDao.add(genre);
		}

		author = authorDao.getByFirstNameAndLastName(author.getFirstName(), author.getLastName());
		genre = genreDao.getByName(genre.getName());
		book = new Book(0, book.getTitle(), author, genre);

		val id = bookDao.add(book);
		return new Book(id, book.getTitle(), author, genre);
	}

	@Override
	public Book getById(long id) {
		try {
			return bookDao.getById(id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Book> getAll() {
		return bookDao.getAll();
	}

	@Override
	public List<Book> getByTitle(String title) {
		return bookDao.getByTitle(title);
	}

	@Override
	public List<Book> getByAuthor(Author author) {
		return bookDao.getByAuthor(author);
	}

	@Override
	public List<Book> getByAuthorFirstNameAndLastName(String firstName, String lastName) {
		return bookDao.getByAuthorFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<Book> getByGenre(Genre genre) {
		return bookDao.getByGenre(genre);
	}

	@Override
	public List<Book> getByGenreName(String genreName) {
		return bookDao.getByGenreName(genreName);
	}


	@Override
	public void update(@NonNull long bookId, @Nullable String title, @Nullable String authorFirstName, @Nullable String authorLastName, @Nullable String genreName) {
		val book = bookDao.getById(bookId);

		title = StringUtils.hasText(title) ? title : book.getTitle();
		authorFirstName = StringUtils.hasText(authorFirstName) ? authorFirstName : book.getAuthor().getFirstName();
		authorLastName = StringUtils.hasText(authorLastName) ? authorLastName : book.getAuthor().getLastName();
		genreName = StringUtils.hasText(genreName) ? genreName : book.getGenre().getName();

		var author = new Author(0, authorFirstName, authorLastName);
		var genre = new Genre(0, genreName);

		if (!authorDao.existsByFirstNameAndLastName(author.getFirstName(), author.getLastName())) {
			authorDao.add(author);
		}

		if (!genreDao.existsByName(genre.getName())) {
			genreDao.add(genre);
		}

		author = authorDao.getByFirstNameAndLastName(author.getFirstName(), author.getLastName());
		genre = genreDao.getByName(genre.getName());

		bookDao.update(new Book(bookId, title, author, genre));
	}

	@Override
	public void deleteById(long id) {
		bookDao.deleteById(id);
	}

	@Override
	public void deleteByTitle(String title) {
		bookDao.deleteByTitle(title);
	}

	@Override
	public void deleteByAuthor(Author author) {
		val persistenceAuthor = authorDao.getByFirstNameAndLastName(author.getFirstName(), author.getLastName());
		val authorsBooks = bookDao.getByAuthor(persistenceAuthor);
		bookDao.delete(authorsBooks);
	}
}