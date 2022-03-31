package ru.vasiliyplatonov.backend.changelog;

import io.mongock.api.annotations.*;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.vasiliyplatonov.backend.domain.Author;
import ru.vasiliyplatonov.backend.domain.Book;
import ru.vasiliyplatonov.backend.domain.BookComment;
import ru.vasiliyplatonov.backend.domain.Genre;
import ru.vasiliyplatonov.backend.repository.BookCommentRepository;
import ru.vasiliyplatonov.backend.repository.BookRepository;

import java.util.List;
import java.util.Set;

import static ru.vasiliyplatonov.backend.changelog.BooksInitializer.ExpectedDataHolder.EXPECTED_BOOKS;
import static ru.vasiliyplatonov.backend.changelog.BooksInitializer.ExpectedDataHolder.EXPECTED_BOOKS_COMMENTS;

@ChangeUnit(id = "books-initializer", order = "1", runAlways = true)
public class BooksInitializer {

	@BeforeExecution
	public void before(MongoOperations mongo) {
		mongo.dropCollection("books");
		mongo.createCollection("books");

		mongo.dropCollection("bookComments");
		mongo.createCollection("bookComments");
	}

	@RollbackBeforeExecution
	public void rollbackBefore(MongoOperations mongo) {
		mongo.dropCollection("books");
		mongo.dropCollection("bookComments");
	}


	@Execution
	public void initBooks(BookRepository bookRepository, BookCommentRepository commentRepository) {
		bookRepository.saveAll(EXPECTED_BOOKS);
		commentRepository.saveAll(EXPECTED_BOOKS_COMMENTS);
	}

	@RollbackExecution
	public void deleteAllBooks(BookRepository bookRepository, BookCommentRepository commentRepository) {
		bookRepository.deleteAll();
		commentRepository.deleteAll();
	}


	public static final class ExpectedDataHolder {
		public static final Genre EXPECTED_GENRE_1 = new Genre("thriller");
		public static final Genre EXPECTED_GENRE_2 = new Genre("drama");
		public static final Genre EXPECTED_GENRE_3 = new Genre("psychological fiction");
		public static final Genre EXPECTED_GENRE_4 = new Genre("poem");

		public static final Author EXPECTED_AUTHOR_1 = new Author("Stephen", "King");
		public static final Author EXPECTED_AUTHOR_2 = new Author("Mikhail", "Lermontov");

		public static final Book EXPECTED_BOOK_1 = new Book("1", "The Green Mile", Set.of(EXPECTED_AUTHOR_1), Set.of(EXPECTED_GENRE_1));
		public static final Book EXPECTED_BOOK_2 = new Book("2", "Rita Hayworth and Shawshank Redemption", Set.of(EXPECTED_AUTHOR_1), Set.of(EXPECTED_GENRE_2));
		public static final Book EXPECTED_BOOK_3 = new Book("3", "A Hero of Our Time", Set.of(EXPECTED_AUTHOR_2), Set.of(EXPECTED_GENRE_3));
		public static final Book EXPECTED_BOOK_4 = new Book("4", "Demon", Set.of(EXPECTED_AUTHOR_2), Set.of(EXPECTED_GENRE_4));

		public static final BookComment EXPECTED_BOOK_COMMENT_1 = new BookComment("1", "That`s awesome", EXPECTED_BOOK_1);
		public static final BookComment EXPECTED_BOOK_COMMENT_2 = new BookComment("2", "That`s awesome", EXPECTED_BOOK_1);
		public static final BookComment EXPECTED_BOOK_COMMENT_3 = new BookComment("3", "That`s awesome", EXPECTED_BOOK_1);
		public static final BookComment EXPECTED_BOOK_COMMENT_4 = new BookComment("4", "That`s awesome", EXPECTED_BOOK_2);
		public static final BookComment EXPECTED_BOOK_COMMENT_5 = new BookComment("5", "That`s awesome", EXPECTED_BOOK_2);
		public static final BookComment EXPECTED_BOOK_COMMENT_6 = new BookComment("6", "That`s awesome", EXPECTED_BOOK_3);

		public static final List<Genre> EXPECTED_GENRES = List.of(EXPECTED_GENRE_1, EXPECTED_GENRE_2, EXPECTED_GENRE_3, EXPECTED_GENRE_4);
		public static final List<Author> EXPECTED_AUTHORS = List.of(EXPECTED_AUTHOR_1, EXPECTED_AUTHOR_2);
		public static final List<Book> EXPECTED_BOOKS = List.of(EXPECTED_BOOK_1, EXPECTED_BOOK_2, EXPECTED_BOOK_3, EXPECTED_BOOK_4);
		public static final List<BookComment> EXPECTED_BOOKS_COMMENTS = List.of(
				EXPECTED_BOOK_COMMENT_1,
				EXPECTED_BOOK_COMMENT_2,
				EXPECTED_BOOK_COMMENT_3,
				EXPECTED_BOOK_COMMENT_4,
				EXPECTED_BOOK_COMMENT_5,
				EXPECTED_BOOK_COMMENT_6
		);

	}

}
