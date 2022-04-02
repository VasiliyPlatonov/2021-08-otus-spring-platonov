package ru.vasiliyplatonov.homework6.repository;

import ru.vasiliyplatonov.homework6.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

	Optional<Genre> getById(long id);

	Optional<Genre> getByName(String name);

	List<Genre> getAll();

	Genre add(Genre genre);
}
