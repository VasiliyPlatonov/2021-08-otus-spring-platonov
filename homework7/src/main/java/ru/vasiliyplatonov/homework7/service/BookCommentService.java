package ru.vasiliyplatonov.homework7.service;

import ru.vasiliyplatonov.homework7.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentService {

	Optional<BookComment> findById(long id);

	List<BookComment> findByBookId(long id);

	BookComment add(BookComment bookComment);

	void delete(long id);

	void update(long parseLong, String text);
}
