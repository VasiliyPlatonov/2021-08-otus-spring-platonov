package ru.vasiliyplatonov.homework6.service;

import ru.vasiliyplatonov.homework6.domain.BookComment;

import java.util.List;

public interface BookCommentService {

	BookComment getById(long id);

	List<BookComment> getByBookId(long id);

	List<BookComment> getByBookId(long id, int max);

	BookComment add(BookComment bookComment);

	void delete(long id);

	void update(long parseLong, String text);
}
