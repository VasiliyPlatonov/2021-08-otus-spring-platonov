package ru.vasiliyplatonov.homework5.dao;

import ru.vasiliyplatonov.homework5.domain.Genre;

import java.util.List;

public interface GenreDao {

	boolean existsByName(String name);

	boolean existsById(long id);

	Genre getById(long id);

	Genre getByName(String name);

	List<Genre> getAll();

	long add(Genre genre);

	void deleteById(long id);

	void update(Genre genre);
}
