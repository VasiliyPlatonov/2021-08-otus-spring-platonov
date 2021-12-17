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

	private Map<String, BookUpdater> bookUpdaters;

	@Override
	public Book update(Book book) {
		return null;
	}

	public Book update(Book book, String... commands) {
//		List<BookUpdater> bookUpdaters = createUpdaters(commands);

		mapCommandsToUpdater(Arrays.stream(commands)).forEach(bookUpdater -> bookUpdater.update(book));

		return book;
	}


	private Stream<BookUpdater> mapCommandsToUpdater(Stream<String> commands) {
		return commands.map(command -> {
			switch (command) {
				case "title":
					return bookUpdaters.get("bookTitleUpdater");

				case "authors":
					return bookUpdaters.get("bookAuthorsUpdater");

				case "genres":
					return bookUpdaters.get("bookGenresUpdater");
			}
			return null;
		});
	}
}
