package ru.vasiliyplatonov.homework6.repository;

import ru.vasiliyplatonov.homework6.domain.Genre;

import java.util.List;

public interface GenreRepository {

	boolean existsByName(String name);

	boolean existsById(long id);

	Genre getById(long id);

	Genre getByName(String name);

	List<Genre> getAll();

	long add(Genre genre);

	void deleteById(long id);

	void update(Genre genre);

}
