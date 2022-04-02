package ru.vasiliyplatonov.homework6.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.BookComment;
import ru.vasiliyplatonov.homework6.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

	@PersistenceContext
	private final EntityManager em;

	@Override
	public Optional<Book> getById(long id) {
		try {
			var book = em.createQuery(
							"select distinct b from Book b " +
									"join fetch b.genres " +
									"join fetch b.authors " +
									"where b.id = :id", Book.class)
					.setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
					.setParameter("id", id)
					.getSingleResult();

			return Optional.of(book);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Book> getAll() {
		return em
				.createQuery("" +
						"select b from Book b " +
						"join fetch b.genres " +
						"join fetch b.authors  ", Book.class)
				.getResultList();
	}

	@Override
	public List<Book> getByTitle(String title) {
		return em.createQuery("select b from Book b where b.title = :title", Book.class)
				.setParameter("title", title)
				.getResultList();
	}

	@Override
	public List<Book> getByGenre(Genre genre) {
		return em
				.createQuery(
						"select distinct b from Book b " +
								"left join fetch b.genres " +
								"left join fetch b.authors " +
								"where :genre member of b.genres", Book.class)
				.setParameter("genre", genre)
				.getResultList();
	}

	@Override
	public List<Book> getByGenreName(String genreName) {
		return em
				.createQuery(
						"select distinct b from Book b " +
								"left join fetch b.genres " +
								"left join fetch b.authors " +
								"where (select g from Genre g where g.name = :genreName) " +
								"member of b.genres", Book.class)
				.setParameter("genreName", genreName)
				.getResultList();
	}

	@Override
	public Book add(Book book) {
		if (book.getId() == null) {
			em.persist(book);
			return book;
		} else {
			return em.merge(book);
		}
	}

	@Override
	public void delete(List<Book> books) {
		val ids = books.stream().map(Book::getId).collect(Collectors.toList());

		em.createQuery("delete from Book b where b.id in :ids")
				.setParameter("ids", ids)
				.executeUpdate();
	}

	@Override
	public void delete(Book book) {
		em.remove(book);
	}

	@Override
	public void update(Book book) {
		em.merge(book);
	}

	@Override
	public List<Book> getByAuthorFirstNameAndLastName(String firstName, String lastName) {
		return  em.createQuery(
						"select b from Book b " +
								"left join fetch b.genres " +
								"left join fetch b.authors " +
								"where (select a from Author a where a.firstName = :firstName and a.lastName = :lastName) " +
								"member of b.authors",
						Book.class)
				.setParameter("firstName", firstName)
				.setParameter("lastName", lastName)
				.getResultList();

	}

	@Override
	public List<BookComment> getBookCommentsByBookId(long bookId) {
		val graph = em.getEntityGraph("book.comments");
		val book = em.find(Book.class, bookId, Map.of("javax.persistence.fetchgraph", graph));
		return new ArrayList<>(book.getBookComments());
	}
}
