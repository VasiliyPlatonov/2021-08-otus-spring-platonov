package ru.vasiliyplatonov.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.backend.domain.Book;
import ru.vasiliyplatonov.backend.domain.Genre;
import ru.vasiliyplatonov.backend.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	@Override
	public Book add(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Optional<Book> findById(String id) {
		return bookRepository.findById(id);
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> findByTitle(String title) {
		return bookRepository.findByTitle(title);
	}

	@Override
	public List<Book> findByAuthorFirstNameAndLastName(String firstName, String lastName) {
		return bookRepository.findByAuthorFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<Book> findByGenre(Genre genre) {
		return bookRepository.findByGenres(Set.of(genre));
	}

	@Override
	public List<Book> findByGenreName(String genreName) {
		return bookRepository.findByGenreName(genreName);
	}

	@Override
	public void update(Book updatedBook) {
		bookRepository.save(updatedBook);
	}

	@Override
	public void deleteById(String id) {
		bookRepository.deleteById(id);
	}

	@Override
	public void deleteByTitle(String title) {
		bookRepository.deleteByTitle(title);
	}

	@Override
	public void deleteByGenreName(String genre) {
		bookRepository.deleteByGenreName(genre);

	}

	@Override
	public void deleteByAuthorFirstNameAndLastName(String firstName, String lastName) {
		bookRepository.deleteByAuthorFirstNameAndLastName(firstName, lastName);
	}
}