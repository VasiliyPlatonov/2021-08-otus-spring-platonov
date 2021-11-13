package ru.vasiliyplatonov.homework5.dao;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.vasiliyplatonov.homework5.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreJdbcDao implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;


    @Override
    public boolean existsById(long id) {
        return Boolean.TRUE.equals(
                jdbc.query(
                        "select 1 from genres where id = :id",
                        Map.of("id", id),
                        ResultSet::next
                ));
    }

    @Override
    public boolean existsByName(String name) {
        return Boolean.TRUE.equals(
                jdbc.query(
                        "select 1 from genres where name = :name",
                        Map.of("name", name),
                        ResultSet::next
                ));
    }

    @Override
    public Genre getById(long id) {
        return jdbc.queryForObject(
                "select id, name from genres where id = :id",
                Map.of("id", id),
                new GenreMapper());
    }

    @Override
    public Genre getByName(String name) {
        return jdbc.queryForObject(
                "select id, name from genres where name = :name",
                Map.of("name", name),
                new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, name from genres", new GenreMapper());
    }

    @Override
    public long add(Genre genre) {
        val params = new MapSqlParameterSource();
        params.addValue("name", genre.getName());
        val keyHolder = new GeneratedKeyHolder();

        jdbc.update("insert into genres (name) values (:name)", params, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void deleteById(long id) {
        val params = Map.of("id", id);
        jdbc.update("delete from genres where id = :id", params);
    }

    @Override
    public void update(Genre genre) {
        val params = new MapSqlParameterSource();
        params.addValue("id", genre.getId());
        params.addValue("name", genre.getName());

        jdbc.update("update genres set name = :name where id = :id", params);
    }


    private static final class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            val id = rs.getLong("id");
            val name = rs.getString("name");
            return new Genre(id, name);
        }
    }
}
