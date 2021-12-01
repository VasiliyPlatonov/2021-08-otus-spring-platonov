package ru.vasiliyplatonov.homework6.repository;

import ru.vasiliyplatonov.homework6.domain.Author;

import java.util.List;

public interface AuthorRepository {

	boolean existsById(long id);

	boolean existsByFirstNameAndLastName(String firstName, String lastName);

	Author getById(long id);

	Author getByFirstNameAndLastName(String firstName, String lastName);

	List<Author> getAll();

	long add(Author author);

	void deleteById(long id);

	void update(Author author);

}
