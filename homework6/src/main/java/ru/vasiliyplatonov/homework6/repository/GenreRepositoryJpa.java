package ru.vasiliyplatonov.homework6.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.vasiliyplatonov.homework6.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {

	@PersistenceContext
	private final EntityManager em;

	@Override
	public Optional<Genre> getById(long id) {
		return Optional.ofNullable(em.find(Genre.class, id));
	}

	@Override
	public Optional<Genre> getByName(String name) {
		try {
			return Optional.of(
					em.createQuery("select g from Genre g where g.name = :name", Genre.class)
							.setParameter("name", name)
							.getSingleResult()
			);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	//todo
	@Override
	public List<Genre> getAll() {
		return null;
	}

	@Override
	public Genre add(Genre genre) {
		if (genre.getId() == null) {
			em.persist(genre);
			return genre;
		} else {
			return em.merge(genre);
		}
	}

}
