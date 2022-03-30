package ru.vasiliyplatonov.homework8.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "bookComments")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookComment {

	@Id
	@EqualsAndHashCode.Include
	private String id;
	private String text;

	@DBRef
	private Book book;


	public BookComment(String text, Book book) {
		this.text = text;
		this.book = book;
	}

	@Override
	public String toString() {
		return "id: " + id + ", book title: " + book.getTitle() + ", text: " + text;
	}

}