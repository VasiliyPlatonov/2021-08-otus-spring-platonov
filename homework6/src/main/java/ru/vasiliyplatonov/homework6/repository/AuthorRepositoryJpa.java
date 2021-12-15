package ru.vasiliyplatonov.homework6.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.vasiliyplatonov.homework6.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryJpa implements AuthorRepository {

	@PersistenceContext
	private final EntityManager em;

	@Override
	public Optional<Author> getById(long id) {
		return Optional.ofNullable(em.find(Author.class, id));
	}

	@Override
	public Optional<Author> getByFirstNameAndLastName(String firstName, String lastName) {
		try {
			return Optional.of(em.createQuery(
							"select a from Author a " +
									"where a.firstName = :firstName and a.lastName = :lastName", Author.class)
					.setParameter("firstName", firstName)
					.setParameter("lastName", lastName)
					.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Author> getAll() {
		return em.createQuery("select a from Author a", Author.class)
				.getResultList();
	}

	@Override
	public Author add(Author author) {
		if (author.getId() == null) {
			em.persist(author);
			return author;
		} else {
			return em.merge(author);
		}
	}
}
