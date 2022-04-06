package ru.vasiliyplatonov.homework8.shell.updater.command.genre;

import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework8.domain.Book;
import ru.vasiliyplatonov.homework8.service.bookprovider.BookProvider;

@Service
public class BookGenresChangeAllCommand extends BookGenresUpdateCommand {

	private static final int KEY = 3;
	private static final String DESCRIPTION = "change all genre from book";

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
