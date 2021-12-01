package ru.vasiliyplatonov.homework6.repository;

import org.springframework.stereotype.Repository;
import ru.vasiliyplatonov.homework6.domain.Author;

import java.util.List;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {
	@Override
	public boolean existsById(long id) {
		return false;
	}

	@Override
	public boolean existsByFirstNameAndLastName(String firstName, String lastName) {
		return false;
	}

	@Override
	public Author getById(long id) {
		return null;
	}

	@Override
	public Author getByFirstNameAndLastName(String firstName, String lastName) {
		return null;
	}

	@Override
	public List<Author> getAll() {
		return null;
	}

	@Override
	public long add(Author author) {
		return 0;
	}

	@Override
	public void deleteById(long id) {

	}

	@Override
	public void update(Author author) {

	}
}
