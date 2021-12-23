package ru.vasiliyplatonov.homework6.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vasiliyplatonov.homework6.domain.Author;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.Genre;
import ru.vasiliyplatonov.homework6.repository.AuthorRepository;
import ru.vasiliyplatonov.homework6.repository.BookRepository;
import ru.vasiliyplatonov.homework6.repository.GenreRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final GenreRepository genreRepository;

	@Override
	public Book add(Book book) {
		return bookRepository.add(book);
	}

	@Override
	public Book getById(long id) {
		val book = bookRepository.getById(id);
		return book.orElse(null);
	}

	@Override
	public Book getByIdFullyCompleted(long id) {
		return null;
	}

	@Override
	public List<Book> getAll() {
		return bookRepository.getAll();
	}

	@Override
	public List<Book> getByTitle(String title) {
		return bookRepository.getByTitle(title);
	}

	@Override
	public List<Book> getByAuthor(Author author) {
		return null;
	}

	@Override
	public List<Book> getByAuthorFirstNameAndLastName(String firstName, String lastName) {
		return bookRepository.getByAuthorFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<Book> getByGenre(Genre genre) {
		return bookRepository.getByGenre(genre);
	}

	@Override
	public List<Book> getByGenreName(String genreName) {
		return bookRepository.getByGenreName(genreName);
	}

	@Override
	public void update(Book updatedBook) {

	}

	@Override
	public void deleteById(long id) {

	}

	@Override
	public void deleteByTitle(String title) {

	}

	@Override
	public void deleteByAuthor(Author author) {

	}
}