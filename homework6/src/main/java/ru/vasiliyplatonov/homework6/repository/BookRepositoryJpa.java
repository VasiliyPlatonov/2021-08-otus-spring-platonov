package ru.vasiliyplatonov.homework6.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import ru.vasiliyplatonov.homework6.domain.Author;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

	@PersistenceContext
	private final EntityManager em;

	private final AuthorRepository authorRepository;
	private final GenreRepository genreRepository;


	@Override
	public Optional<Book> getById(long id) {
		return Optional.of(em.find(Book.class, id));
	}

	@Override
	public List<Book> getAll() {
		return em
				.createQuery("select b from Book b join fetch b.authors", Book.class)
				.getResultList();
	}

	@Override
	public List<Book> getByTitle(String title) {
		return em.createQuery("select b from Book b where b.title = :title", Book.class)
				.setParameter("title", title)
				.getResultList();
	}

	@Override
	public List<Book> getByAuthor(Author author) {
		return null;
	}

	@Override
	public List<Book> getByGenre(Genre genre) {
		return em
				.createQuery("select b from Book b where :genre member of b.genres", Book.class)
				.setParameter("genre", genre)
				.getResultList();
	}

	@Override
	public List<Book> getByGenreName(String genreName) {
		return em
				.createQuery(
						"select b " +
								"from Book b " +
								"where (select g from Genre g where g.name = :genreName) " +
								"member of b.genres", Book.class)
				.setParameter("genreName", genreName)
				.getResultList();
	}

	@Override
	public Book add(Book book) {
		val authors = book.getAuthors();
		val genres = book.getGenres();

		val persistBook = new Book(book.getTitle(), new ArrayList<>(), new ArrayList<>());

		authors.forEach(author -> {
			author = authorRepository
					.getByFirstNameAndLastName(author.getFirstName(), author.getLastName())
					.orElse(author);

			persistBook.getAuthors().add(author);
			author.getBooks().add(persistBook);
		});

		genres.forEach(genre -> {
			genre = genreRepository
					.getByName(genre.getName())
					.orElse(genre);

			persistBook.getGenres().add(genre);
		});

		if (book.getId() == null) {
			em.persist(persistBook);
			return persistBook;
		} else {
			persistBook.setId(book.getId());
			return em.merge(persistBook);
		}
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
	public List<Book> getByAuthorFirstNameAndLastName(String firstName, String lastName) {

		val authors = em.createQuery(
						"select distinct a from Author a " +
								"join fetch a.books " +
								"where a.firstName in :names " +
								"and a.lastName in :names", Author.class)
				.setParameter("names", List.of(firstName, lastName))
				.getResultList();

		return authors.stream()
				.flatMap(a -> a.getBooks().stream())
				.collect(Collectors.toList());
	}
}
