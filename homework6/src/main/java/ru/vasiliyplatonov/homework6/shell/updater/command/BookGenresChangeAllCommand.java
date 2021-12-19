package ru.vasiliyplatonov.homework6.shell.updater.command;

import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.service.bookprovider.BookProvider;

@Service
public class BookGenresChangeAllCommand extends BookUpdateCommand {

	private static final int KEY = 3;
	private static final String DESCRIPTION = "change all";

	private final BookProvider bookProvider;


	public BookGenresChangeAllCommand(BookProvider bookProvider) {
		super(KEY, DESCRIPTION);
		this.bookProvider = bookProvider;
	}

	@Override
	public Book update(Book book) {
		book.setGenres(bookProvider.getGenres());
		return book;
	}

}
