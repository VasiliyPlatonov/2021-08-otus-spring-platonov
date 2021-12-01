package ru.vasiliyplatonov.homework6.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.vasiliyplatonov.homework6.domain.Author;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

	@PersistenceContext
	private final EntityManager em;

	@Override
	public Book getById(long id) {
		return null;
	}

	@Override
	public List<Book> getAll() {

		return em
				.createQuery(
						"select b " +
								"from Book b join fetch b.authors",
						Book.class)
				.getResultList();
	}

	@Override
	public List<Book> getByTitle(String title) {
		return null;
	}

	@Override
	public List<Book> getByAuthor(Author author) {
		return null;
	}

	@Override
	public List<Book> getByGenre(Genre genre) {
		return null;
	}

	@Override
	public long add(Book book) {
		return 0;
	}

	@Override
	public void deleteById(long id) {

	}

	@Override
	public void deleteByAuthor(Author author) {

	}

	@Override
	public void delete(List<Book> books) {

	}

	@Override
	public void delete(Book book) {

	}

	@Override
	public void deleteByTitle(String title) {

	}

	@Override
	public void update(Book book) {

	}

	@Override
	public List<Book> getByGenreName(String genreName) {
		return null;
	}

	@Override
	public List<Book> getByAuthorFirstNameAndLastName(String firstName, String lastName) {
		return null;
	}
}
