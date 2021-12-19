package ru.vasiliyplatonov.homework6.service.bookprovider;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Author;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.Genre;
import ru.vasiliyplatonov.homework6.service.ioservice.IOService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookInteractiveProvider implements BookProvider {

	private final IOService ioService;

	public Book getBook() {
		val title = getTitle();
		val authors = getAuthors();
		val genres = getGenres();

		return new Book(title, authors, genres);
	}

	public List<Genre> getGenres() {
		var genres = new ArrayList<Genre>();
		var command = "+";

		while (command.equals("+") || genres.isEmpty()) {
			try {
				ioService.out("Enter the genre: ");
				String genre = ioService.readLine();
				if (genre.trim().isEmpty()) {
					continue;
				}
				genres.add(new Genre(genre));
				ioService.out("Enter '+' for another yet author or click enter for the next step: ");
				command = ioService.readLine();
			} catch (Exception e) {
				ioService.outLine("Error has occurred when trying to add the genre. Please try again.");
			}
		}

		return genres;
	}

	public List<Author> getAuthors() {
		var authors = new ArrayList<Author>();
		var command = "+";

		while (command.equals("+") || authors.isEmpty()) {
			ioService.out("Enter the author (<firstname> <lastname>): ");
			val fullName = ioService.readLine();
			if (fullName.trim().isEmpty()) {
				continue;
			}
			try {
				val fullNameArr = fullName.split(" ");
				val firstName = fullNameArr[0];
				val lastName = fullNameArr[1];
				authors.add(new Author(firstName, lastName));
			} catch (Exception e) {
				ioService.outLine("Error has occurred when trying to add the author. Please try again.");
			}

			ioService.out("Enter '+' for another yet author or click enter for the next step: ");
			command = ioService.readLine();
		}

		return authors;
	}

	public String getTitle() {
		var title = "";

		while (title.isEmpty()) {
			ioService.out("Enter the books title: ");
			title = ioService.readLine();
		}

		return title;
	}
}
