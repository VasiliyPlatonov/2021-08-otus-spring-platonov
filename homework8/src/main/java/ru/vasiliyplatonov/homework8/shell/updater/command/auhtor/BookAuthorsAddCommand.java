package ru.vasiliyplatonov.homework8.shell.updater.command.auhtor;

import lombok.val;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework8.domain.Book;
import ru.vasiliyplatonov.homework8.service.bookprovider.BookProvider;

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
		val authors = bookProvider.getAuthors();
		book.getAuthors().addAll(authors);
//		authors.forEach(a->a.getBooks().add(book));

		return book;
	}
}
