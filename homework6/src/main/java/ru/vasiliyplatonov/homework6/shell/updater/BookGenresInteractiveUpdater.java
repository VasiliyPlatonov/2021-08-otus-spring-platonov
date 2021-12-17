package ru.vasiliyplatonov.homework6.shell.updater;

import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Book;

@Service("bookGenresUpdater")
public class BookGenresInteractiveUpdater implements BookUpdater{

	@Override
	public Book update(Book book) {
		System.out.println("BookGenresInteractiveUpdater#update");
		return book;
	}

}
