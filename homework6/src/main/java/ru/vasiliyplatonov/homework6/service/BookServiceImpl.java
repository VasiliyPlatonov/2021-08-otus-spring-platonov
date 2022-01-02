package ru.vasiliyplatonov.homework6.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.Genre;
import ru.vasiliyplatonov.homework6.repository.BookRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;


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
		return bookRepository.getByIdFullyCompleted(id).orElse(null);
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
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void update(Book book) {
		bookRepository.update(book);
	}

	@Override
	public void deleteById(long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public void deleteByTitle(String title) {
		bookRepository.deleteByTitle(title);
	}

}