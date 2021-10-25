package ru.vasiliyplatonov.homework5.dao;

import ru.vasiliyplatonov.homework5.domain.Author;

import java.util.List;

public interface AuthorDao {
	Author getById(long id);

	List<Author> getAll();

	long add(Author author);

	void deleteById(long id);

	void update(Author author);
}
