package ru.vasiliyplatonov.backend.service;

import ru.vasiliyplatonov.backend.domain.Book;
import ru.vasiliyplatonov.backend.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookService {

	Book add(Book book);

	Optional<Book> findById(String id);

	List<Book> findAll();

	List<Book> findByTitle(String title);

	List<Book> findByAuthorFirstNameAndLastName(String firstName, String lastName);

	List<Book> findByGenre(Genre genre);

	List<Book> findByGenreName(String genreName);

	void update(Book updatedBook);

	void deleteById(String id);

	void deleteByTitle(String title);

	void deleteByGenreName(String genre);

	void deleteByAuthorFirstNameAndLastName(String firstName, String lastName);
}
