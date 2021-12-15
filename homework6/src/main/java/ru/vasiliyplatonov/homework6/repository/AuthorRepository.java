package ru.vasiliyplatonov.homework6.repository;

import ru.vasiliyplatonov.homework6.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

	Optional<Author> getById(long id);

	Optional<Author> getByFirstNameAndLastName(String firstName, String lastName);

	List<Author> getAll();

	Author add(Author author);

}
