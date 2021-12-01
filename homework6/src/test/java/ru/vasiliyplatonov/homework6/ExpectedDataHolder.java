package ru.vasiliyplatonov.homework6;

import ru.vasiliyplatonov.homework6.domain.Author;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.Genre;

import java.util.List;

public class ExpectedDataHolder {
	public static final Genre EXPECTED_GENRE_1 = new Genre(1, "thriller");
	public static final Genre EXPECTED_GENRE_2 = new Genre(2, "drama");
	public static final Genre EXPECTED_GENRE_3 = new Genre(3, "psychological fiction");
	public static final Genre EXPECTED_GENRE_4 = new Genre(4, "poem");


	public static final Author EXPECTED_AUTHOR_1 = new Author(1, "Stephen", "King");
	public static final Author EXPECTED_AUTHOR_2 = new Author(2, "Mikhail", "Lermontov");

	public static final Book EXPECTED_BOOK_1 = new Book(1, "The Green Mile", List.of(EXPECTED_AUTHOR_1), List.of(EXPECTED_GENRE_1));
	public static final Book EXPECTED_BOOK_2 = new Book(2, "Rita Hayworth and Shawshank Redemption", List.of(EXPECTED_AUTHOR_1), List.of(EXPECTED_GENRE_2));
	public static final Book EXPECTED_BOOK_3 = new Book(3, "A Hero of Our Time", List.of(EXPECTED_AUTHOR_2), List.of(EXPECTED_GENRE_3));
	public static final Book EXPECTED_BOOK_4 = new Book(4, "Demon", List.of(EXPECTED_AUTHOR_2), List.of(EXPECTED_GENRE_4));

	public static final List<Genre> EXPECTED_GENRES = List.of(EXPECTED_GENRE_1, EXPECTED_GENRE_2, EXPECTED_GENRE_3, EXPECTED_GENRE_4);
	public static final List<Author> EXPECTED_AUTHORS = List.of(EXPECTED_AUTHOR_1, EXPECTED_AUTHOR_2);
	public static final List<Book> EXPECTED_BOOKS = List.of(EXPECTED_BOOK_1, EXPECTED_BOOK_2, EXPECTED_BOOK_3, EXPECTED_BOOK_4);

	static {
		EXPECTED_AUTHOR_1.setBooks(List.of(EXPECTED_BOOK_1, EXPECTED_BOOK_2));
		EXPECTED_AUTHOR_2.setBooks(List.of(EXPECTED_BOOK_3, EXPECTED_BOOK_4));
	}
}
