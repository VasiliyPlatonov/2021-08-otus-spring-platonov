package ru.vasiliyplatonov.homework8.service;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vasiliyplatonov.homework8.domain.BookComment;
import ru.vasiliyplatonov.homework8.repository.BookCommentRepository;
import ru.vasiliyplatonov.homework8.repository.BookRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.vasiliyplatonov.homework8.changelog.BooksInitializer.ExpectedDataHolder.*;

@SpringBootTest
@DisplayName("BookCommentServiceImpl tests: ")
class BookCommentServiceImplTest {

	@Autowired
	private BookCommentServiceImpl bookCommentService;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookCommentRepository commentRepository;

	@BeforeEach
	void beforeEach() {
		bookRepository.saveAll(EXPECTED_BOOKS);
		commentRepository.saveAll(EXPECTED_BOOKS_COMMENTS);
	}

	@AfterEach
	void afterEach() {
		bookRepository.deleteAll();
		commentRepository.deleteAll();
	}


	@Test
	@DisplayName("should return comment of book by id")
	void findById() {
		val existingCommentId = EXPECTED_BOOK_COMMENT_1.getId();

		val actualComment = bookCommentService.findById(existingCommentId).orElseThrow();

		assertThat(actualComment)
				.usingRecursiveComparison()
				.isEqualTo(EXPECTED_BOOK_COMMENT_1);
	}

	@Test
	@DisplayName("should return all comments of book by book id")
	void findByBookId() {
		val existingBookId = EXPECTED_BOOK_COMMENT_1.getBook().getId();

		val actualComments = bookCommentService.findByBookId(existingBookId);

		assertThat(actualComments)
				.usingRecursiveComparison()
				.isEqualTo(List.of(EXPECTED_BOOK_COMMENT_1, EXPECTED_BOOK_COMMENT_2, EXPECTED_BOOK_COMMENT_3));

	}

	@Test
	@DisplayName("should return comments of book by book id and max count")
	void findByBookIdMaxCount() {
		val existingBookId = EXPECTED_BOOK_COMMENT_1.getBook().getId();

		val actualComments = bookCommentService.findByBookId(existingBookId, 1);

		assertThat(actualComments)
				.usingRecursiveComparison()
				.isEqualTo(List.of(EXPECTED_BOOK_COMMENT_1));
	}

	@Test
	@DisplayName("should correct add book comment")
	void add() {
		val existingBookId = EXPECTED_BOOK_1.getId();
		val book = bookRepository.findById(existingBookId).orElseThrow();

		val addedComment = bookCommentService.add(new BookComment("some new comment", book));

		assertThat(addedComment.getId()).isNotNull();
	}

	@Test
	@DisplayName("should correct delete book comment by id")
	void delete() {
		val deletedCommentId = EXPECTED_BOOK_COMMENT_1.getId();

		bookCommentService.delete(deletedCommentId);

		val shouldBeEmpty = bookCommentService.findById(deletedCommentId);

		assertThat(shouldBeEmpty).isEmpty();
	}

	@Test
	@DisplayName("should correct delete book comments by book`s id")
	void deleteByBookId() {
		val existingBookId = EXPECTED_BOOK_1.getId();

		bookCommentService.deleteByBookId(existingBookId);

		val shouldBeEmpty = bookCommentService.findByBookId(existingBookId);

		assertThat(shouldBeEmpty.isEmpty()).isTrue();
	}

	@Test
	@DisplayName("should correct update book comment by id")
	void update() {
		val updatedCommentId = EXPECTED_BOOK_COMMENT_1.getId();
		val someNewText = "someNewText";

		bookCommentService.update(updatedCommentId, someNewText);

		val actualComment = bookCommentService
				.findById(updatedCommentId)
				.orElseThrow();

		assertThat(actualComment.getText()).isEqualTo(someNewText);
	}
}