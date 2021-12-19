package ru.vasiliyplatonov.homework6.shell.updater.command;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import ru.vasiliyplatonov.homework6.domain.Book;

@RequiredArgsConstructor
@EqualsAndHashCode(of = {"key"})
public abstract class BookUpdateCommand {

	public final int key;
	public final String description;

	public abstract Book update(Book book);

	@Override
	public String toString() {
		return key + " - " + description;
	}
}
