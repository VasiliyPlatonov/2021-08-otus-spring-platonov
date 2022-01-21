package ru.vasiliyplatonov.homework7.service;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.vasiliyplatonov.homework7.domain.BookComment;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static ru.vasiliyplatonov.homework7.ExpectedDataHolder.*;

@SpringBootTest
@Sql(scripts = "/sql/data.sql")
@Sql(scripts = "/sql/cleanup-data.sql", executionPhase = AFTER_TEST_METHOD)
@DisplayName("BookCommentServiceImpl tests: ")
class BookCommentServiceImplTest {

	@Autowired
	private BookCommentServiceImpl bookCommentService;

	@Autowired
	private BookService bookService;

	@Test
	@DisplayName("should return all comments of book by book id")
	void shouldReturnBookCommentByBookId() {
		val existingBookId = EXPECTED_BOOK_1.getId();
		val expectedComments = List.of(EXPECTED_BOOK_COMMENT_1, EXPECTED_BOOK_COMMENT_2, EXPECTED_BOOK_COMMENT_3);

		val actualComments = bookCommentService.findByBookId(existingBookId);

		assertThat(actualComments)
				.usingRecursiveComparison()
				.ignoringFields("book.authors", "book.genres")
				.isEqualTo(expectedComments);
	}

	@Test
	@DisplayName("should return comments of book by book id and max count")
	void shouldReturnSpecifiedCountOfBookComment() {
		val max = 2;
		val existingBookId = EXPECTED_BOOK_1.getId();
		val expectedComments = List.of(EXPECTED_BOOK_COMMENT_1, EXPECTED_BOOK_COMMENT_2);

		val actualComments = bookCommentService.findByBookId(existingBookId, max);

		assertThat(actualComments)
				.usingRecursiveComparison()
				.ignoringFields("book.authors", "book.genres")
				.isEqualTo(expectedComments);
	}

	@Test
//	@Transactional
//	@Rollback
	@DisplayName("should correct add book comment")
	void shouldCorrectAddBookComment() {
		val existingBookId = EXPECTED_BOOK_1.getId();
		val book = bookService.findById(existingBookId).orElseThrow();

		val addedComment = bookCommentService.add(new BookComment("some new comment", book));

		assertThat(addedComment.getId()).isNotNull();
	}

	@Test
//	@Transactional
//	@Rollback
	@DisplayName("should correct delete book comment by id")
	void shouldCorrectDeleteBookCommentById() {
		val deletedCommentId = EXPECTED_BOOK_COMMENT_1.getId();

		bookCommentService.delete(deletedCommentId);

		val shouldBeNull = bookCommentService.findById(deletedCommentId);

		assertThat(shouldBeNull).isEmpty();
	}

	@Test
//	@Transactional
//	@Rollback
	@DisplayName("should correct update book comment by id")
	void shouldCorrectUpdateBookCommentById() {
		val updatedCommentId = EXPECTED_BOOK_COMMENT_1.getId();
		val someNewText = "someNewText";

		bookCommentService.update(updatedCommentId, someNewText);

		val actualComment = bookCommentService
				.findById(updatedCommentId)
				.orElseThrow();

		assertThat(actualComment.getText()).isEqualTo(someNewText);
	}
}