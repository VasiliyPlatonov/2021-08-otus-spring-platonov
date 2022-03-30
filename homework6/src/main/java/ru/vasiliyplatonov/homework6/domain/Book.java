package ru.vasiliyplatonov.homework6.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "book.authors", attributeNodes = @NamedAttributeNode(value = "authors"))
@NamedEntityGraph(name = "book.genres", attributeNodes = @NamedAttributeNode(value = "genres"))
@NamedEntityGraph(name = "book.comments", attributeNodes = @NamedAttributeNode(value = "bookComments"))
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
	private Set<Author> authors = new HashSet<>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "books_genres",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genre> genres = new HashSet<>();


	@OneToMany(
			mappedBy = "book",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Set<BookComment> bookComments = new HashSet<>();

	public Book(String title, Set<Author> authors, Set<Genre> genres) {
		this.title = title;
		this.genres = genres;
		this.authors = authors;
	}

	public Book(long id, String title, Set<Author> authors, Set<Genre> genres) {
		this.id = id;
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

