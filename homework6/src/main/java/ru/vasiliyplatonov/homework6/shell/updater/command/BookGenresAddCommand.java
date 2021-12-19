package ru.vasiliyplatonov.homework6.shell.updater.command;

import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.service.bookprovider.BookInteractiveProvider;
import ru.vasiliyplatonov.homework6.service.bookprovider.BookProvider;

@Service
public class BookGenresAddCommand extends BookUpdateCommand {

	private static final int KEY = 1;
	private static final String DESCRIPTION = "add descr";

	private final BookProvider bookProvider;

	public BookGenresAddCommand(BookInteractiveProvider bookProvider) {
		super(KEY, DESCRIPTION);
		this.bookProvider = bookProvider;
	}

	@Override
	public Book update(Book book) {
		book.getGenres().addAll(bookProvider.getGenres());
		return book;
	}

}
