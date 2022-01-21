package ru.vasiliyplatonov.homework7.shell.updater;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework7.domain.Book;
import ru.vasiliyplatonov.homework7.service.ioservice.IOService;
import ru.vasiliyplatonov.homework7.shell.updater.command.auhtor.BookAuthorsUpdateCommand;

import java.util.List;

@Service("bookAuthorsUpdater")
@RequiredArgsConstructor
public class BookAuthorsInteractiveUpdater implements BookUpdater {

	private final IOService io;
	private final List<BookAuthorsUpdateCommand> updateCommands;

	@Override
	public Book update(Book book) {
		while (true) {
			io.outLine("\nFor update books authors enter the key of command or 'exit': ");
			updateCommands.forEach(io::outLine);

			val userCommand = io.readLine();

			if (userCommand.equals("exit")) {
				break;
			}

			updateCommands.stream()
					.filter(cmd -> Integer.parseInt(userCommand) == cmd.key)
					.forEach(cmd -> cmd.update(book));
		}

		return book;
	}
}
