package ru.vasiliyplatonov.homework6.shell.updater;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Book;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookInteractiveUpdater implements BookUpdater {

	private final Map<String, BookUpdater> bookUpdaters;

	@Override
	public Book update(Book book) {
		return null;
	}

	public Book update(Book book, String... commands) {
		mapCommandsToUpdater(Arrays.stream(commands))
				.forEach(bookUpdater -> bookUpdater.update(book));

		System.out.println("updated book: " + book.toString() + " genres: " + book.getGenres() + " authors: " + book.getAuthors().toString());

		return book;
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
