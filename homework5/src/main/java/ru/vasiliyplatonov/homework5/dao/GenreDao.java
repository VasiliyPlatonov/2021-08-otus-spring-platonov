package ru.vasiliyplatonov.homework5.dao;

import ru.vasiliyplatonov.homework5.domain.Genre;

import java.util.List;

public interface GenreDao {

	Genre getById(long id);

	List<Genre> getAll();

	long add(Genre genre);

	void deleteById(long id);

	void update(Genre genre);
}
