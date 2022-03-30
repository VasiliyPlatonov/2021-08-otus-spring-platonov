package ru.vasiliyplatonov.homework8.shell.updater;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework8.domain.Book;
import ru.vasiliyplatonov.homework8.service.ioservice.IOService;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookInteractiveUpdater implements BookUpdater {

	private final Map<String, BookUpdater> bookUpdaters;
	private final IOService ioService;

	@Override
	public Book update(Book book) {

		val commands = getCommands();

		mapCommandsToUpdater(Arrays.stream(commands))
				.forEach(bookUpdater -> bookUpdater.update(book));

		System.out.println("updated book: " + book.toString() + " genres: " + book.getGenres() + " authors: " + book.getAuthors().toString());

		return book;
	}

	private String[] getCommands() {
		ioService.outLine("Please enter the books properties to update: ");
		ioService.outLine("example: \"title, genres\" for update books title and genres.");

		return ioService.readLine().split("[, ]+");
	}


	private Stream<BookUpdater> mapCommandsToUpdater(Stream<String> commands) {
		return commands.map(command -> {
			switch (command) {
				case BookUpdateCommands.TITLE:
					return bookUpdaters.get("bookTitleUpdater");

				case BookUpdateCommands.AUTHORS:
					return bookUpdaters.get("bookAuthorsUpdater");

				case BookUpdateCommands.GENRES:
					return bookUpdaters.get("bookGenresUpdater");
			}
			return null;
		});
	}

	public static class BookUpdateCommands {
		public static final String TITLE = "title";
		public static final String GENRES = "genres";
		public static final String AUTHORS = "authors";
	}
}
