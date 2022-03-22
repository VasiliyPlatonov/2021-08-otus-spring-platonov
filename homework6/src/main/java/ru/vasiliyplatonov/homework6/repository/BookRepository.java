package ru.vasiliyplatonov.homework6.repository;

import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.BookComment;
import ru.vasiliyplatonov.homework6.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
	Optional<Book> getById(long id);

	Optional<Book> getByIdFullyCompleted(long id);

	List<Book> getAll();

	List<Book> getByTitle(String title);

	List<Book> getByGenre(Genre genre);

	Book add(Book book);

	void delete(List<Book> books);

	void delete(Book book);

	void update(Book book);

	List<Book> getByGenreName(String genreName);

	List<Book> getByAuthorFirstNameAndLastName(String firstName, String lastName);

	List<BookComment> getBookCommentsByBookId(long bookId);
}
