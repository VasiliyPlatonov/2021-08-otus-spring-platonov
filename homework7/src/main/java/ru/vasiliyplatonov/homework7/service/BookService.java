package ru.vasiliyplatonov.homework7.service;

import ru.vasiliyplatonov.homework7.domain.Book;
import ru.vasiliyplatonov.homework7.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookService {

	Book add(Book book);

	Optional<Book> findById(long id);

	List<Book> findAll();

	List<Book> findByTitle(String title);

	List<Book> findByAuthorFirstNameAndLastName(String firstName, String lastName);

	List<Book> findByGenre(Genre genre);

	List<Book> findByGenreName(String genreName);

	void update(Book updatedBook);

	void deleteById(long id);
}
