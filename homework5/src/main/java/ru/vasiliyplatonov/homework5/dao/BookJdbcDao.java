package ru.vasiliyplatonov.homework5.dao;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.vasiliyplatonov.homework5.domain.Author;
import ru.vasiliyplatonov.homework5.domain.Book;
import ru.vasiliyplatonov.homework5.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@SuppressWarnings({"SqlNoDataSourceInspection"})
public class BookJdbcDao implements BookDao {

	private final NamedParameterJdbcOperations jdbc;


	@Override
	public Book getById(long id) {
		val params = Map.of("id", id);

		return jdbc.queryForObject("select " +
				"b.id as book_id," +
				"b.title as title," +
				"a.id as author_id," +
				"first_name as author_first_name," +
				"last_name as author_last_name," +
				"g.id as genre_id, " +
				"g.name as genre_name " +
				"from books b " +
				"inner join authors a on b.author_id = a.id " +
				"inner join genres g on b.genre_id = g.id " +
				"where b.id = :id", params, new BookMapper());
	}

	@Override
	public List<Book> getAll() {
		return jdbc.query("select " +
				"b.id as book_id," +
				"b.title as title," +
				"a.id as author_id," +
				"first_name as author_first_name," +
				"last_name as author_last_name," +
				"g.id as genre_id, " +
				"g.name as genre_name " +
				"from books b " +
				"inner join authors a on b.author_id = a.id " +
				"inner join genres g on b.genre_id = g.id", new BookMapper());
	}

	@Override
	public List<Book> getByTitle(String title) {
		val params = Map.of("title", title);

		return jdbc.query("select " +
				"b.id as book_id," +
				"b.title as title," +
				"a.id as author_id," +
				"first_name as author_first_name," +
				"last_name as author_last_name," +
				"g.id as genre_id, " +
				"g.name as genre_name " +
				"from books b " +
				"inner join authors a on b.author_id = a.id " +
				"inner join genres g on b.genre_id = g.id " +
				"where b.title = :title", params, new BookMapper());
	}

	@Override
	public List<Book> getByAuthor(Author author) {
		val params = Map.of("authorId", author.getId());
		return jdbc.query("select " +
				"b.id as book_id," +
				"b.title as title," +
				"a.id as author_id," +
				"first_name as author_first_name," +
				"last_name as author_last_name," +
				"g.id as genre_id, " +
				"g.name as genre_name " +
				"from books b " +
				"inner join authors a on b.author_id = a.id " +
				"inner join genres g on b.genre_id = g.id " +
				"where b.author_id = :authorId", params, new BookMapper());
	}

	@Override
	public List<Book> getByGenre(Genre genre) {
		val params = Map.of("genreId", genre.getId());

		return jdbc.query("select " +
				"b.id as book_id," +
				"b.title as title," +
				"a.id as author_id," +
				"first_name as author_first_name," +
				"last_name as author_last_name," +
				"g.id as genre_id, " +
				"g.name as genre_name " +
				"from books b " +
				"inner join authors a on b.author_id = a.id " +
				"inner join genres g on b.genre_id = g.id " +
				"where b.genre_id = :genreId", params, new BookMapper());
	}

	@Override
	public long add(Book book) {
		val params = new MapSqlParameterSource();
		params.addValue("title", book.getTitle());
		params.addValue("authorId", book.getAuthor().getId());
		params.addValue("genreId", book.getGenre().getId());

		val keyHolder = new GeneratedKeyHolder();

		jdbc.update("insert into books (title, author_id, genre_id) values (:title, :authorId, :genreId)", params, keyHolder);

		return keyHolder.getKey().longValue();
	}

	@Override
	public void deleteById(long id) {
		jdbc.update("delete from books where id = :id", Map.of("id", id));
	}

	@Override
	public void update(Book book) {
		val params = new MapSqlParameterSource();
		params.addValue("id", book.getId());
		params.addValue("title", book.getTitle());
		params.addValue("authorId", book.getAuthor().getId());
		params.addValue("genreId", book.getGenre().getId());

		jdbc.update("update books" +
						" set" +
						"     title = :title," +
						"     author_id = :authorId," +
						"     genre_id = :genreId" +
						" where id = :id",
				params);
	}

	private static class BookMapper implements RowMapper<Book> {
		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

			val authorId = rs.getLong("author_id");
			val authorFirstName = rs.getString("author_first_name");
			val authorLastName = rs.getString("author_last_name");

			val genreId = rs.getLong("genre_id");
			val genreName = rs.getString("genre_name");

			val id = rs.getLong("book_id");
			val title = rs.getString("title");

			val book = new Book(
					id, title,
					new Author(authorId, authorFirstName, authorLastName),
					new Genre(genreId, genreName));

			return book;
		}
	}
}
