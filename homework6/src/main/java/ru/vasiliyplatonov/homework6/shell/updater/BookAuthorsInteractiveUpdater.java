package ru.vasiliyplatonov.homework6.shell.updater;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.service.ioservice.IOService;

@Service("bookAuthorsUpdater")
@RequiredArgsConstructor
public class BookAuthorsInteractiveUpdater implements BookUpdater{

	private final IOService io;


	@Override
	public Book update(Book book) {

		System.out.println("BookAuthorsInteractiveUpdater#update");

		return book;
	}
}
