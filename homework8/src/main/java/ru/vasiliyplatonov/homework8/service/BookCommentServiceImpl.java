package ru.vasiliyplatonov.homework8.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework8.domain.BookComment;
import ru.vasiliyplatonov.homework8.repository.BookCommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

	private final BookCommentRepository commentRepository;

	@Override
	public Optional<BookComment> findById(String id) {
		return commentRepository.findById(id);
	}

	@Override
	public List<BookComment> findByBookId(String id) {
		return commentRepository.findByBookId(id);
	}

	@Override
	public List<BookComment> findByBookId(String id, int max) {
		return commentRepository.findByBookId(id, Pageable.ofSize(max));
	}

	@Override
	public BookComment add(BookComment bookComment) {
		return commentRepository.save(bookComment);
	}

	@Override
	public void delete(String id) {
		commentRepository.deleteById(id);
	}

	@Override
	public void deleteByBookId(String bookId) {
		commentRepository.deleteByBookId(bookId);
	}

	@Override
	public void update(String id, String text) {
		val comment = findById(id).orElseThrow();
		comment.setText(text);

		commentRepository.save(comment);
	}
}
