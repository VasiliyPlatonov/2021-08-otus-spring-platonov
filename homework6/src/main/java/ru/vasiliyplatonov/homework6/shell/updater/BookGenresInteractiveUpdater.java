package ru.vasiliyplatonov.homework6.shell.updater;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.service.ioservice.IOService;
import ru.vasiliyplatonov.homework6.shell.updater.command.genre.BookGenresUpdateCommand;

import java.util.List;

@Service("bookGenresUpdater")
@RequiredArgsConstructor
public class BookGenresInteractiveUpdater implements BookUpdater {

	private final IOService io;
	private final List<BookGenresUpdateCommand> updateCommands;

	@Override
	public Book update(Book book) {
		while (true) {
			io.outLine("\nFor update books genres enter the key of command or 'exit': ");
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
