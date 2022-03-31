package ru.vasiliyplatonov.backend.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.vasiliyplatonov.backend.domain.Book;
import ru.vasiliyplatonov.backend.repository.BookCommentRepository;

@Component
@RequiredArgsConstructor
public class BookMongoEventListener extends AbstractMongoEventListener<Book> {

	private final BookCommentRepository commentRepository;

	@Override
	public void onAfterDelete(AfterDeleteEvent<Book> event) {
		super.onAfterDelete(event);

		val bookId = event.getSource().get("_id").toString();

		deleteAllBookCommentsByBookId(bookId);
	}

	private void deleteAllBookCommentsByBookId(String id) {
		commentRepository.deleteByBookId(id);
	}
}
