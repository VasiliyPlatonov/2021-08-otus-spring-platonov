package ru.vasiliyplatonov.homework6.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "BookComment")
@Table(name = "book_comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "bookComment.book", attributeNodes = @NamedAttributeNode(value = "book"))
public class BookComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
		BookComment bookComment = (BookComment) o;
		if (null == id) return false;
		return id.equals(bookComment.id);
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += (null != getId())
				? getId().hashCode() * 31
				: (null != getBook())
				? getBook().hashCode()
				: 0;

		return hashCode;
	}
}
