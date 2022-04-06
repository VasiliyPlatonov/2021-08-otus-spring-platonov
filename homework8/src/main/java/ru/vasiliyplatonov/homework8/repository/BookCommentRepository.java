package ru.vasiliyplatonov.homework8.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.vasiliyplatonov.homework8.domain.BookComment;

import java.util.List;

public interface BookCommentRepository extends CrudRepository<BookComment, String> {

	List<BookComment> findByBookId(String id);

	List<BookComment> findByBookId(String id, Pageable pageable);

	void deleteByBookId(String id);

}
