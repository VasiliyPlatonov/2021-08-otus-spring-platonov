package ru.vasiliyplatonov.homework5.dao;

import ru.vasiliyplatonov.homework5.domain.Book;

import java.util.List;

public interface BookDao {
	Book getById(long id);

	List<Book> getAll();

	long add(Book book);

	void deleteById(long id);

	void update(Book book);
}
