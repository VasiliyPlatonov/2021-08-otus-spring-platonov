package ru.vasiliyplatonov.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;


@Document("books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

	@Id
	@EqualsAndHashCode.Include
	private String id;
	private String title;
	private Set<Author> authors;
	private Set<Genre> genres;


	public Book(String title, Set<Author> authors, Set<Genre> genres) {
		this.title = title;
		this.authors = authors;
		this.genres = genres;
	}
}

