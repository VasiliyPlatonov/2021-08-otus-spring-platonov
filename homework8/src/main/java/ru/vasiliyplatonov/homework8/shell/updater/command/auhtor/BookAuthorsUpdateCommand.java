package ru.vasiliyplatonov.homework8.shell.updater.command.auhtor;

import ru.vasiliyplatonov.homework8.shell.updater.command.BookUpdateCommand;

public abstract class BookAuthorsUpdateCommand extends BookUpdateCommand {

	public BookAuthorsUpdateCommand(int key, String description) {
		super(key, description);
	}
}
