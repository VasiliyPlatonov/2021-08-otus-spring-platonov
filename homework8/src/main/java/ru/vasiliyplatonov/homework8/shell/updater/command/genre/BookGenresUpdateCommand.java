package ru.vasiliyplatonov.homework8.shell.updater.command.genre;

import ru.vasiliyplatonov.homework8.shell.updater.command.BookUpdateCommand;

public abstract class BookGenresUpdateCommand extends BookUpdateCommand {

	public BookGenresUpdateCommand(int key, String description) {
		super(key, description);
	}
}
