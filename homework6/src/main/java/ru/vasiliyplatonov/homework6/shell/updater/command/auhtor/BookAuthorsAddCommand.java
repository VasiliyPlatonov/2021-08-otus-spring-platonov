package ru.vasiliyplatonov.homework6.shell.updater.command.auhtor;

import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.service.bookprovider.BookProvider;

@Service
public class BookAuthorsAddCommand extends BookAuthorsUpdateCommand {

	private static final int KEY = 1;
	private static final String DESCRIPTION = "add author to book";

	private final BookProvider bookProvider;

	public BookAuthorsAddCommand(BookProvider bookProvider) {
		super(KEY, DESCRIPTION);
		this.bookProvider = bookProvider;
	}


	@Override
	public Book update(Book book) {
		book.getAuthors().addAll(bookProvider.getAuthors());
		return book;
	}
}
