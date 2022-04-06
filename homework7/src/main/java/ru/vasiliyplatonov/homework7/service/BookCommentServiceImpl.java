package ru.vasiliyplatonov.homework7.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vasiliyplatonov.homework7.domain.BookComment;
import ru.vasiliyplatonov.homework7.repository.BookCommentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

	private final BookCommentRepository bookCommentRepository;


	@Override
	@Transactional(readOnly = true)
	public Optional<BookComment> findById(long id) {
		return bookCommentRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookComment> findByBookId(long id) {
		return bookCommentRepository.findByBookId(id);
	}

	@Override
	@Transactional
	public BookComment add(BookComment bookComment) {
		return bookCommentRepository.save(bookComment);
	}

	@Override
	@Transactional
	public void delete(long id) throws NoSuchElementException {
		bookCommentRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void update(long id, String text) {
		val bookComment = bookCommentRepository.findById(id).orElseThrow();
		bookComment.setText(text);
	}
}
