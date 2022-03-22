package ru.vasiliyplatonov.homework6.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.BookComment;
import ru.vasiliyplatonov.homework6.domain.Genre;
import ru.vasiliyplatonov.homework6.repository.AuthorRepository;
import ru.vasiliyplatonov.homework6.repository.BookRepository;
import ru.vasiliyplatonov.homework6.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final GenreRepository genreRepository;


	@Override
	@Transactional
	public Book add(Book book) {
		val genres = book.getGenres();
		val authors = book.getAuthors();

		val persistBook = new Book(book.getTitle(), new ArrayList<>(), new ArrayList<>());

		authors.forEach(author -> {
			author = authorRepository
					.getByFirstNameAndLastName(author.getFirstName(), author.getLastName())
					.orElse(author);

			persistBook.getAuthors().add(author);
			author.getBooks().add(persistBook);
		});

		genres.forEach(genre -> {
			genre = genreRepository
					.getByName(genre.getName())
					.orElse(genre);

			persistBook.getGenres().add(genre);
		});

		return bookRepository.add(persistBook);
	}

	@Override
	@Transactional(readOnly = true)
	public Book getById(long id) {
		val book = bookRepository.getById(id);
		return book.orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Book getByIdFullyCompleted(long id) {
		return bookRepository.getByIdFullyCompleted(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> getAll() {
		return bookRepository.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> getByTitle(String title) {
		return bookRepository.getByTitle(title);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> getByAuthorFirstNameAndLastName(String firstName, String lastName) {
		return bookRepository.getByAuthorFirstNameAndLastName(firstName, lastName);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> getByGenre(Genre genre) {
		return bookRepository.getByGenre(genre);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> getByGenreName(String genreName) {
		return bookRepository.getByGenreName(genreName);
	}

	@Override
	@Transactional
	public void update(Book book) {
		bookRepository.update(book);
	}

	@Override
	public void delete(Book book) {
		bookRepository.delete(book);
	}

	@Override
	@Transactional
	public void deleteById(long id) throws NoSuchElementException {
		val book = bookRepository.getById(id).orElseThrow();
		bookRepository.delete(book);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookComment> getBookCommentsByBookId(Long bookId) {
		return bookRepository.getBookCommentsByBookId(bookId);
	}
}