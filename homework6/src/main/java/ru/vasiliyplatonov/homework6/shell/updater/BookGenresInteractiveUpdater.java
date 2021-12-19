package ru.vasiliyplatonov.homework6.shell.updater;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.service.ioservice.IOService;
import ru.vasiliyplatonov.homework6.shell.updater.command.BookUpdateCommand;

import java.util.List;

@Service("bookGenresUpdater")
@RequiredArgsConstructor
public class BookGenresInteractiveUpdater implements BookUpdater {

	private final IOService io;
	private final List<BookUpdateCommand> updateCommands;

	@Override
	public Book update(Book book) {
		System.out.println("BookGenresInteractiveUpdater#update");

		while (true) {
			io.outLine("\nEnter the key of command or 'exit': ");
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
