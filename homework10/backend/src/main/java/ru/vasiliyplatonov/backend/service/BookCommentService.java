package ru.vasiliyplatonov.backend.service;

import ru.vasiliyplatonov.backend.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentService {

	Optional<BookComment> findById(String id);

	List<BookComment> findByBookId(String id);

	List<BookComment> findByBookId(String id, int max);

	BookComment add(BookComment bookComment);

	void delete(String id);

	void deleteByBookId(String bookId);

	void update(String id, String text);
}
