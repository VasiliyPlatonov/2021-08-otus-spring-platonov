package ru.vasiliyplatonov.homework7.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasiliyplatonov.homework7.domain.BookComment;

import java.util.List;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {

	List<BookComment> findByBookId(long id);

	List<BookComment> findByBookId(long id, Pageable pageable);

}
