package ru.vasiliyplatonov.homework6.shell.updater.command.genre;

import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.service.bookprovider.BookProvider;

@Service
public class BookGenresRemoveCommand extends BookGenresUpdateCommand {

	private static final int KEY = 2;
	private static final String DESCRIPTION = "remove genre from book";

	private final BookProvider bookProvider;


	public BookGenresRemoveCommand(BookProvider bookProvider) {
		super(KEY, DESCRIPTION);
		this.bookProvider = bookProvider;
	}

	@Override
	public Book update(Book book) {
		book.getGenres().removeAll(bookProvider.getGenres());
		return book;
	}

}
