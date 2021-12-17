package ru.vasiliyplatonov.homework6.shell.updater;

import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Book;

@Service("bookTitleUpdater")
public class BookTitleInteractiveUpdater implements BookUpdater {

	@Override
	public Book update(Book book) {
		System.out.println("BookTitleInteractiveUpdater#update");
		return book;
	}
}
