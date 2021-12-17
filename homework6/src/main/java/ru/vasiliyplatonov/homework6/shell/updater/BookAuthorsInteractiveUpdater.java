package ru.vasiliyplatonov.homework6.shell.updater;

import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Book;

@Service("bookAuthorsUpdater")
public class BookAuthorsInteractiveUpdater implements BookUpdater{

	@Override
	public Book update(Book book) {

		System.out.println("BookAuthorsInteractiveUpdater#update");

		return book;
	}
}
