package ru.vasiliyplatonov.homework6.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "book.authors", attributeNodes = @NamedAttributeNode(value = "authors"))
@NamedEntityGraph(name = "book.genres", attributeNodes = @NamedAttributeNode(value = "genres"))
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title", nullable = false)
	private String title;

	@ManyToMany
	@JoinTable(name = "books_authors",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> authors;

	@Fetch(FetchMode.SUBSELECT)
	@ManyToMany
	@JoinTable(name = "books_genres",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private List<Genre> genres; // For unidirectional collections, Sets are the best choice because they generate the most efficient SQL statements.

	public long getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public List<Author> getAuthors() {
		return this.authors;
	}

	public List<Genre> getGenres() {
		return this.genres;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public String toString() {
		return "Book(id=" + this.getId() + ", title=" + this.getTitle() + ")";
	}
}

