package ru.vasiliyplatonov.homework8.shell.updater;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework8.domain.Book;
import ru.vasiliyplatonov.homework8.service.ioservice.IOService;

@Service("bookTitleUpdater")
@RequiredArgsConstructor
public class BookTitleInteractiveUpdater implements BookUpdater {

	private final IOService io;

	@Override
	public Book update(Book book) {
		var newTitle = "";

		while (newTitle.isBlank()) {
			io.outLine("\nEnter new title: ");
			newTitle = io.readLine();
		}

		book.setTitle(newTitle);

		return book;
	}
}
