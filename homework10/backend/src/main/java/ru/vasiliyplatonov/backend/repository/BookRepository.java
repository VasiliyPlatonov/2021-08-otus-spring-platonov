package ru.vasiliyplatonov.backend.repository;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vasiliyplatonov.backend.domain.Book;
import ru.vasiliyplatonov.backend.domain.Genre;

import java.util.List;
import java.util.Set;

public interface BookRepository extends MongoRepository<Book, String> {

	List<Book> findByGenres(Set<Genre> genres);

	List<Book> findByTitle(String title);

	void deleteByTitle(String title);

	@Query("{ 'authors': { 'firstName': :#{#firstName}, 'lastName': :#{#lastName} } }")
	List<Book> findByAuthorFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

	@Query("{ genres: { name: :#{#name}}}")
	List<Book> findByGenreName(@Param("name") String genreName);

	@DeleteQuery(value = "{ genres: { name: :#{#name}}}")
	void deleteByGenreName(@Param("name") String genreName);

	@DeleteQuery(value = "{ 'authors': { 'firstName': :#{#firstName}, 'lastName': :#{#lastName} } }")
	void deleteByAuthorFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
