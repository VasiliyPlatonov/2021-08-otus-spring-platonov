package ru.vasiliyplatonov.homework6.service;

import ru.vasiliyplatonov.homework6.domain.Author;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.Genre;

import java.util.List;

public interface BookService {

	Book add(Book book);

	Book getById(long id);

	List<Book> getAll();

	List<Book> getByTitle(String title);

	List<Book> getByAuthor(Author author);

	List<Book> getByAuthorFirstNameAndLastName(String firstName, String lastName);

	List<Book> getByGenre(Genre genre);

	List<Book> getByGenreName(String genreName);

	void update(long bookId, String title, String authorFirstName, String authorLastName, String genreName);

	void deleteById(long id);

	void deleteByTitle(String title);

	void deleteByAuthor(Author author);

}
