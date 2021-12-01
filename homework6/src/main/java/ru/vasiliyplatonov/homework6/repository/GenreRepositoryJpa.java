package ru.vasiliyplatonov.homework6.repository;

import org.springframework.stereotype.Repository;
import ru.vasiliyplatonov.homework6.domain.Genre;

import java.util.List;

@Repository
public class GenreRepositoryJpa implements GenreRepository{
	@Override
	public boolean existsByName(String name) {
		return false;
	}

	@Override
	public boolean existsById(long id) {
		return false;
	}

	@Override
	public Genre getById(long id) {
		return null;
	}

	@Override
	public Genre getByName(String name) {
		return null;
	}

	@Override
	public List<Genre> getAll() {
		return null;
	}

	@Override
	public long add(Genre genre) {
		return 0;
	}

	@Override
	public void deleteById(long id) {

	}

	@Override
	public void update(Genre genre) {

	}
}
