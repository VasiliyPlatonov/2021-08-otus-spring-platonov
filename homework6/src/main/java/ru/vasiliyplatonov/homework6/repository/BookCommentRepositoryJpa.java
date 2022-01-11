package ru.vasiliyplatonov.homework6.repository;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import ru.vasiliyplatonov.homework6.domain.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BookCommentRepositoryJpa implements BookCommentRepository {

	@PersistenceContext
	private final EntityManager em;

	@Override
	public Optional<BookComment> getById(long id) {
		val graph = em.getEntityGraph("bookComment.book");

		return Optional.ofNullable(
				em.find(BookComment.class, id, Map.of("javax.persistence.fetchgraph", graph))
		);
	}

	@Override
	public List<BookComment> getByBookId(long bookId) {
		return em.createQuery("" +
								"select bc from BookComment bc " +
								"left join fetch bc.book " +
								"where bc.book.id = :bookId",
						BookComment.class)
				.setParameter("bookId", bookId)
				.getResultList();
	}

	@Override
	public List<BookComment> getByBookId(long bookId, int max) {
		return em.createQuery("" +
								"select bc from BookComment bc " +
								"left join fetch bc.book " +
								"where bc.book.id = :bookId",
						BookComment.class)
				.setParameter("bookId", bookId)
				.setMaxResults(max)
				.getResultList();
	}

	@Override
	public BookComment add(BookComment bookComment) {
		if (bookComment.getId() == null) {
			em.persist(bookComment);
			return bookComment;
		} else {
			return em.merge(bookComment);
		}
	}

	@Override
	public void delete(long id) {
		em.createQuery("delete from BookComment bc where bc.id = :id")
				.setParameter("id", id)
				.executeUpdate();
	}

	@Override
	public void update(long id, String text) {
		val bookComment = em.find(BookComment.class, id);
		bookComment.setText(text);
	}
}
