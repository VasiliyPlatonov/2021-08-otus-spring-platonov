package ru.vasiliyplatonov.homework6.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "book.authors", attributeNodes = @NamedAttributeNode(value = "authors"))
@NamedEntityGraph(name = "book.genres", attributeNodes = @NamedAttributeNode(value = "genres"))
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "books_authors",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> authors;

	@Fetch(FetchMode.SUBSELECT)
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "books_genres",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private List<Genre> genres;

	public Book(String title, List<Author> authors, List<Genre> genres) {
		this.title = title;
		this.genres = genres;
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "Book(id=" + this.getId() + ", title=" + this.getTitle() + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return title.equals(book.title) && authors.equals(book.authors) && Objects.equals(genres, book.genres);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, authors, genres);
	}
}

