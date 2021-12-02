package ru.vasiliyplatonov.homework6.repository;

import ru.vasiliyplatonov.homework6.domain.Author;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
	Optional<Book> getById(long id);

	List<Book> getAll();

	List<Book> getByTitle(String title);

	List<Book> getByAuthor(Author author);

	List<Book> getByGenre(Genre genre);

	long add(Book book);

	void deleteById(long id);

	void deleteByAuthor(Author author);

	void delete(List<Book> books);

	void delete(Book book);

	void deleteByTitle(String title);

	void update(Book book);

	List<Book> getByGenreName(String genreName);

	List<Book> getByAuthorFirstNameAndLastName(String firstName, String lastName);
}
