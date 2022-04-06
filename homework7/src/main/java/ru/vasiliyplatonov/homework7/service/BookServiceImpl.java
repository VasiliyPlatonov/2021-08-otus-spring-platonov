package ru.vasiliyplatonov.homework7.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vasiliyplatonov.homework7.domain.Book;
import ru.vasiliyplatonov.homework7.domain.Genre;
import ru.vasiliyplatonov.homework7.repository.AuthorRepository;
import ru.vasiliyplatonov.homework7.repository.BookRepository;
import ru.vasiliyplatonov.homework7.repository.GenreRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final GenreRepository genreRepository;


	@Override
	@Transactional
	public Book add(Book book) {
		val genres = book.getGenres().stream().map(
						g -> genreRepository
								.findByName(g.getName())
								.orElseGet(() -> genreRepository.save(g)))
				.collect(Collectors.toSet());


		val authors = book.getAuthors().stream().map(
						a -> authorRepository
								.findByFirstNameAndLastName(a.getFirstName(), a.getLastName())
								.orElseGet(() -> authorRepository.save(a)))
				.collect(Collectors.toSet());

		book.setGenres(genres);
		book.setAuthors(authors);

		return bookRepository.save(book);
	}

	@Override
	public Optional<Book> findById(long id) {
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
	@Transactional(readOnly = true)
	public List<Book> findByAuthorFirstNameAndLastName(String firstName, String lastName) {
		val authorOpt = authorRepository.findByFirstNameAndLastName(firstName, lastName);

		if (authorOpt.isPresent()) {
			return bookRepository.findByAuthorsIn(Set.of(authorOpt.get()));
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public List<Book> findByGenre(Genre genre) {
		return bookRepository.findByGenresIn(Set.of(genre));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findByGenreName(String genreName) {
		val genreOpt = genreRepository.findByName(genreName);

		if (genreOpt.isPresent()) {
			return bookRepository.findByGenresIn(Set.of(genreOpt.get()));
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional
	public void update(Book book) {
		val persistenceBook = bookRepository.findById(book.getId()).orElseThrow();

		persistenceBook.setTitle(book.getTitle());

		val genres = book.getGenres().stream().map(
						g -> genreRepository
								.findByName(g.getName())
								.orElseGet(() -> genreRepository.save(g)))
				.collect(Collectors.toSet());


		val authors = book.getAuthors().stream().map(
						a -> authorRepository
								.findByFirstNameAndLastName(a.getFirstName(), a.getLastName())
								.orElseGet(() -> authorRepository.save(a)))
				.collect(Collectors.toSet());

		persistenceBook.setGenres(genres);
		persistenceBook.setAuthors(authors);
	}

	@Override
	public void deleteById(long id) throws NoSuchElementException {
		bookRepository.deleteById(id);
	}

}