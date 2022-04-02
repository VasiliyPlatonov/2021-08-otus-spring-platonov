package ru.vasiliyplatonov.backend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.vasiliyplatonov.backend.domain.BookComment;

import java.util.List;

public interface BookCommentRepository extends CrudRepository<BookComment, String> {

	List<BookComment> findByBookId(String id);

	List<BookComment> findByBookId(String id, Pageable pageable);

	void deleteByBookId(String id);

}
