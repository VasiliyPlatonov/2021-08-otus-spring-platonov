package ru.vasiliyplatonov.homework6.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.BookComment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.vasiliyplatonov.homework6.ExpectedDataHolder.*;

@DataJpaTest
@DisplayName("Book comment JPA repository ")
@Import({BookCommentRepositoryJpa.class, BookRepositoryJpa.class, GenreRepositoryJpa.class, AuthorRepositoryJpa.class}) //todo
class BookCommentRepositoryJpaTest {

	@Autowired
	private BookCommentRepositoryJpa commentRepo;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("should return bookComment by id")
	void shouldReturnBookCommentById() {
		val actualCommentOpt = commentRepo.getById(EXPECTED_BOOK_COMMENT_1.getId());

		assertThat(actualCommentOpt)
				.usingRecursiveComparison()
				.isEqualTo(Optional.of(EXPECTED_BOOK_COMMENT_1));
	}

	@Test
	@DisplayName("should return all comments of book by book id")
	void shouldReturnBookCommentByBookId() {
		val existingBookId = EXPECTED_BOOK_1.getId();
		val expectedComments = List.of(EXPECTED_BOOK_COMMENT_1, EXPECTED_BOOK_COMMENT_2, EXPECTED_BOOK_COMMENT_3);

		val actualComments = commentRepo.getByBookId(existingBookId);

		assertThat(actualComments)
				.usingRecursiveComparison()
				.isEqualTo(expectedComments);
	}

	@Test
	@DisplayName("should return comments of book by book id and max count")
	void shouldReturnSpecifiedCountOfBookComment() {
		val max = 2;
		val existingBookId = EXPECTED_BOOK_1.getId();
		val expectedComments = List.of(EXPECTED_BOOK_COMMENT_1, EXPECTED_BOOK_COMMENT_2);

		val actualComments = commentRepo.getByBookId(existingBookId, max);

		assertThat(actualComments)
				.usingRecursiveComparison()
				.isEqualTo(expectedComments);
	}


	@Test
	@DisplayName("should correct add book comment")
	void shouldCorrectAddBookComment() {
		val existingBookId = EXPECTED_BOOK_1.getId();
		val book = em.find(Book.class, existingBookId);

		val addedComment = commentRepo.add(new BookComment("some new comment", book));

		em.flush();
		em.clear();

		val expectedComment = em.find(BookComment.class, addedComment.getId());

		assertThat(expectedComment).isNotNull();
	}


	@Test
	@DisplayName("should correct delete book comment by id")
	void shouldCorrectDeleteBookCommentById() {
		val deletedCommentId = EXPECTED_BOOK_COMMENT_1.getId();

		val bookComment = em.find(BookComment.class, deletedCommentId);

		commentRepo.delete(bookComment);

		em.flush();
		em.clear();

		val shouldBeNull = em.find(BookComment.class, deletedCommentId);

		assertThat(shouldBeNull).isNull();
	}

	@Test
	@DisplayName("should correct update book comment by id")
	void shouldCorrectUpdateBookCommentById() {
		val existingCommentId = EXPECTED_BOOK_COMMENT_1.getId();

		val bookComment = em.find(BookComment.class, existingCommentId);
		val someNewText = "some new text";
		bookComment.setText(someNewText);

		commentRepo.update(bookComment);

		em.flush();
		em.clear();

		val actualComment = em.find(BookComment.class, existingCommentId);

		assertThat(actualComment.getText()).isEqualTo(someNewText);

	}
}