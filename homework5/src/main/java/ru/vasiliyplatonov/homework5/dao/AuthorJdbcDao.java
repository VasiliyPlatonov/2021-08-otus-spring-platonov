package ru.vasiliyplatonov.homework5.dao;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.vasiliyplatonov.homework5.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorJdbcDao implements AuthorDao {

	private final NamedParameterJdbcOperations jdbc;

	@Override
	public Author getById(long id) {
		val params = Map.of("id", id);
		return jdbc.queryForObject("select * from authors where id = :id", params, new AuthorMapper());
	}

	@Override
	public List<Author> getAll() {
		return jdbc.query("select id, first_name, last_name from authors", new AuthorMapper());
	}

	@Override
	public long add(Author author) {
		val params = new MapSqlParameterSource();
		params.addValue("firstName", author.getFirstName());
		params.addValue("lastName", author.getLastName());

		val keyHolder = new GeneratedKeyHolder();

		jdbc.update("insert into authors (first_name, last_name) values (:firstName, :lastName)", params, keyHolder);

		return keyHolder.getKey().longValue();
	}

	@Override
	public void deleteById(long id) {
		jdbc.update("delete from authors where id = :id", Map.of("id", id));
	}

	@Override
	public void update(Author author) {
		val params = new MapSqlParameterSource();
		params.addValue("id", author.getId());
		params.addValue("firstName", author.getFirstName());
		params.addValue("lastName", author.getLastName());

		jdbc.update("update authors set first_name = :firstName, last_name = :lastName where id = :id", params);
	}

	private static class AuthorMapper implements RowMapper<Author> {

		@Override
		public Author mapRow(ResultSet rs, int rowNum) throws SQLException {

			val id = rs.getLong("id");
			val firstName = rs.getString("first_name");
			val lastName = rs.getString("last_name");

			return new Author(id, firstName, lastName);
		}

	}
}
