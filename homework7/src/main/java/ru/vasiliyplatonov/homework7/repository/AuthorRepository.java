package ru.vasiliyplatonov.homework7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasiliyplatonov.homework7.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

}
