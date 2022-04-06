package ru.vasiliyplatonov.homework7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasiliyplatonov.homework7.domain.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

	Optional<Genre> findByName(String name);

}
