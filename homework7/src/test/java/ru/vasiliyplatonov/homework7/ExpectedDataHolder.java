package ru.vasiliyplatonov.homework7;

import ru.vasiliyplatonov.homework7.domain.Author;
import ru.vasiliyplatonov.homework7.domain.Book;
import ru.vasiliyplatonov.homework7.domain.BookComment;
import ru.vasiliyplatonov.homework7.domain.Genre;

import java.util.List;
import java.util.Set;

public class ExpectedDataHolder {
	public static final Genre EXPECTED_GENRE_1 = new Genre(1L, "thriller");
	public static final Genre EXPECTED_GENRE_2 = new Genre(2L, "drama");
	public static final Genre EXPECTED_GENRE_3 = new Genre(3L, "psychological fiction");
	public static final Genre EXPECTED_GENRE_4 = new Genre(4L, "poem");


	public static final Author EXPECTED_AUTHOR_1 = new Author(1L, "Stephen", "King");
	public static final Author EXPECTED_AUTHOR_2 = new Author(2L, "Mikhail", "Lermontov");

	public static final Book EXPECTED_BOOK_1 = new Book(1L, "The Green Mile", Set.of(EXPECTED_AUTHOR_1), Set.of(EXPECTED_GENRE_1));
	public static final Book EXPECTED_BOOK_2 = new Book(2L, "Rita Hayworth and Shawshank Redemption", Set.of(EXPECTED_AUTHOR_1), Set.of(EXPECTED_GENRE_2));
	public static final Book EXPECTED_BOOK_3 = new Book(3L, "A Hero of Our Time", Set.of(EXPECTED_AUTHOR_2), Set.of(EXPECTED_GENRE_3));
	public static final Book EXPECTED_BOOK_4 = new Book(4L, "Demon", Set.of(EXPECTED_AUTHOR_2), Set.of(EXPECTED_GENRE_4));

	public static final BookComment EXPECTED_BOOK_COMMENT_1 = new BookComment(1L, "That`s awesome", EXPECTED_BOOK_1);
	public static final BookComment EXPECTED_BOOK_COMMENT_2 = new BookComment(2L, "That`s awesome", EXPECTED_BOOK_1);
	public static final BookComment EXPECTED_BOOK_COMMENT_3 = new BookComment(3L, "That`s awesome", EXPECTED_BOOK_1);
	public static final BookComment EXPECTED_BOOK_COMMENT_4 = new BookComment(4L, "That`s awesome", EXPECTED_BOOK_2);
	public static final BookComment EXPECTED_BOOK_COMMENT_5 = new BookComment(5L, "That`s awesome", EXPECTED_BOOK_2);
	public static final BookComment EXPECTED_BOOK_COMMENT_6 = new BookComment(6L, "That`s awesome", EXPECTED_BOOK_3);

	public static final List<Genre> EXPECTED_GENRES = List.of(EXPECTED_GENRE_1, EXPECTED_GENRE_2, EXPECTED_GENRE_3, EXPECTED_GENRE_4);
	public static final List<Author> EXPECTED_AUTHORS = List.of(EXPECTED_AUTHOR_1, EXPECTED_AUTHOR_2);
	public static final List<Book> EXPECTED_BOOKS = List.of(EXPECTED_BOOK_1, EXPECTED_BOOK_2, EXPECTED_BOOK_3, EXPECTED_BOOK_4);

	static {
		EXPECTED_AUTHOR_1.setBooks(Set.of(EXPECTED_BOOK_1, EXPECTED_BOOK_2));
		EXPECTED_AUTHOR_2.setBooks(Set.of(EXPECTED_BOOK_2, EXPECTED_BOOK_4));
	}
}
