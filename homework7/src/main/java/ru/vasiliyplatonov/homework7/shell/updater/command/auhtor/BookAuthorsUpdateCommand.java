package ru.vasiliyplatonov.homework7.shell.updater.command.auhtor;

import ru.vasiliyplatonov.homework7.shell.updater.command.BookUpdateCommand;

public abstract class BookAuthorsUpdateCommand extends BookUpdateCommand {

	public BookAuthorsUpdateCommand(int key, String description) {
		super(key, description);
	}
}
