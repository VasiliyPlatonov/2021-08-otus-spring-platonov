package ru.vasiliyplatonov.homework6.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vasiliyplatonov.homework6.domain.BookComment;
import ru.vasiliyplatonov.homework6.repository.BookCommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

	private final BookCommentRepository bookCommentRepository;


	@Override
	@Transactional(readOnly = true)
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
	public void delete(long id) {
		bookCommentRepository.delete(id);
	}

	@Override
	@Transactional
	public void update(long id, String text) {
		bookCommentRepository.update(id, text);
	}
}
