package ru.vasiliyplatonov.homework5.dao;

import ru.vasiliyplatonov.homework5.domain.Author;

import java.util.List;

public interface AuthorDao {

	boolean existsById(long id);

	boolean existsByFirstNameAndLastName(String firstName, String lastName);

	Author getById(long id);

	Author getByFirstNameAndLastName(String firstName, String lastName);

	List<Author> getAll();

	long add(Author author);

	void deleteById(long id);

	void update(Author author);
}
