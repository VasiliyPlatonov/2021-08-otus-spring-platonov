package ru.vasiliyplatonov.homework6.repository;

import ru.vasiliyplatonov.homework6.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentRepository {

	Optional<BookComment> getById(long id);

	List<BookComment> getByBookId(long bookId);

	List<BookComment> getByBookId(long id, int max);

	BookComment add(BookComment bookComment);

	void delete(BookComment bookComment);

	void update(BookComment comment);
}
