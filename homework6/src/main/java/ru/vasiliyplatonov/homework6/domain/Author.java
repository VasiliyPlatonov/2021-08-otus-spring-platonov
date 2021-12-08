package ru.vasiliyplatonov.homework6.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@ManyToMany(mappedBy = "authors")
	private List<Book> books;

	public Author(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.books = new ArrayList<>();
	}

	public Author(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.books = new ArrayList<>();
	}

	public String toString() {
		return "Author(id=" + this.getId() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ")";
	}
}
