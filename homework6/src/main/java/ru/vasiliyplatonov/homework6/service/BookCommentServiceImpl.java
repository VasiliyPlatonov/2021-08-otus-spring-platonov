package ru.vasiliyplatonov.homework6.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vasiliyplatonov.homework6.domain.BookComment;
import ru.vasiliyplatonov.homework6.repository.BookCommentRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

	private final BookCommentRepository bookCommentRepository;


	@Override
	public BookComment getById(long id) {
		val commentOpt = bookCommentRepository.getById(id);
		return commentOpt.orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookComment> getByBookId(long id) {
		return bookCommentRepository.getByBookId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookComment> getByBookId(long id, int max) {
		return bookCommentRepository.getByBookId(id, max);
	}

	@Override
	@Transactional
	public BookComment add(BookComment bookComment) {
		return bookCommentRepository.add(bookComment);
	}

	@Override
	@Transactional
	public void delete(long id) throws NoSuchElementException {
		val comment = bookCommentRepository.getById(id).orElseThrow();
		bookCommentRepository.delete(comment);
	}

	@Override
	@Transactional
	public void update(long id, String text) {
		val comment = bookCommentRepository.getById(id).orElseThrow();
		comment.setText(text);

		bookCommentRepository.update(comment);
	}
}
