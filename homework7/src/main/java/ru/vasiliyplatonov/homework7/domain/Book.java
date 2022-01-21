package ru.vasiliyplatonov.homework7.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@ManyToMany(cascade = {CascadeType.REMOVE})
	@JoinTable(name = "books_authors",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> authors;

	@ManyToMany(cascade = {CascadeType.REMOVE})
	@JoinTable(name = "books_genres",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genre> genres;

	public Book(String title, Set<Author> authors, Set<Genre> genres) {
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
		if (null == id) return false;
		return id.equals(book.id);
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? getTitle().hashCode() : getId().hashCode() * 31;
		return hashCode;
	}
}

