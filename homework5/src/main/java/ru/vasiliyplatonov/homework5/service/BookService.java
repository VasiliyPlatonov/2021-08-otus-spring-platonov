package ru.vasiliyplatonov.homework5.service;

import ru.vasiliyplatonov.homework5.domain.Author;
import ru.vasiliyplatonov.homework5.domain.Book;
import ru.vasiliyplatonov.homework5.domain.Genre;

import java.util.List;

public interface BookService {

	Book add(Book book);

	Book getById(long id);

	List<Book> getAll();

	List<Book> getByTitle(String title);

	List<Book> getByAuthor(Author author);

	List<Book> getByAuthor(String author);

	List<Book> getByGenre(Genre genre);

	List<Book> getByGenre(String genre);

	void update(Book book);

	void deleteById(long id);

}
