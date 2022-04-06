package ru.vasiliyplatonov.homework8.shell.updater.command.auhtor;

import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework8.domain.Book;
import ru.vasiliyplatonov.homework8.service.bookprovider.BookProvider;

@Service
public class BookAuthorsRemoveCommand extends BookAuthorsUpdateCommand {
	private static final int KEY = 2;
	private static final String DESCRIPTION = "remove author from book";

	private final BookProvider bookProvider;

	public BookAuthorsRemoveCommand(BookProvider bookProvider) {
		super(KEY, DESCRIPTION);
		this.bookProvider = bookProvider;
	}

	@Override
	public Book update(Book book) {
		book.getAuthors().removeAll(bookProvider.getAuthors());
		return book;
	}
}
