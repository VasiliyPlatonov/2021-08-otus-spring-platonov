package ru.vasiliyplatonov.homework7.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book_comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "text")
	private String text;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id")
	private Book book;


	public BookComment(String text, Book book) {
		this.text = text;
		this.book = book;
	}

	@Override
	public String toString() {
		return "id: " + id + ", text: " + text;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BookComment that = (BookComment) o;
		if (null == id) return false;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}
}