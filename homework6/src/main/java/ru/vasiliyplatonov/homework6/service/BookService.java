package ru.vasiliyplatonov.homework6.service;

import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.Genre;

import java.util.List;

public interface BookService {

	Book add(Book book);

	Book getById(long id);

	Book getByIdFullyCompleted(long id);

	List<Book> getAll();

	List<Book> getByTitle(String title);

	List<Book> getByAuthorFirstNameAndLastName(String firstName, String lastName);

	List<Book> getByGenre(Genre genre);

	List<Book> getByGenreName(String genreName);

	void update(Book updatedBook);

	void deleteById(long id);

	void deleteByTitle(String title);
}
