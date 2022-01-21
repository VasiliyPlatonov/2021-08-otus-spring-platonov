package ru.vasiliyplatonov.homework7.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasiliyplatonov.homework7.domain.Author;
import ru.vasiliyplatonov.homework7.domain.Book;
import ru.vasiliyplatonov.homework7.domain.Genre;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {

	@Override
	@EntityGraph(attributePaths = {"genres", "authors"})
	Optional<Book> findById(Long id);

	@EntityGraph(attributePaths = {"genres", "authors"})
	List<Book> findByGenresIn(Set<Genre> genres);

	@EntityGraph(attributePaths = {"genres", "authors"})
	List<Book> findByTitle(String title);

	@EntityGraph(attributePaths = {"genres", "authors"})
	List<Book> findByAuthorsIn(Set<Author> authors);

	@Override
	@EntityGraph(attributePaths = {"genres", "authors"})
	List<Book> findAll();
}
