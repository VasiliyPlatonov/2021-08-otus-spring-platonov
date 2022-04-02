package ru.vasiliyplatonov.homework6.shell.updater.command.genre;

import ru.vasiliyplatonov.homework6.shell.updater.command.BookUpdateCommand;

public abstract class BookGenresUpdateCommand extends BookUpdateCommand {

	public BookGenresUpdateCommand(int key, String description) {
		super(key, description);
	}
}
