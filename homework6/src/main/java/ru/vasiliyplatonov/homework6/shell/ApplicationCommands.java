package ru.vasiliyplatonov.homework6.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.vasiliyplatonov.homework6.domain.Author;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.service.BookService;
import ru.vasiliyplatonov.homework6.service.ioservice.IOService;
import ru.vasiliyplatonov.homework6.shell.table.renderer.TableRenderer;
import ru.vasiliyplatonov.homework6.shell.updater.BookInteractiveUpdater;

import javax.validation.constraints.NotBlank;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

	private final BookService bookService;
	private final TableRenderer<Book> bookTableRenderer;
	private final BookIOProvider bookIOProvider;
	private final IOService ioService;
	private final BookInteractiveUpdater bookInteractiveUpdater;


	@ShellMethod(value = "Show book", key = {"book show"}, group = "book", prefix = "-")
	public String showBook(@ShellOption(help = "Possible values are: '-id', '-genre', '-author'", defaultValue = "all") String filterName,
	                       @ShellOption(defaultValue = "") String filterValue) {

		switch (filterName) {
			case "all": {
				return bookService.getAll().toString();
//				return bookTableRenderer.render(
//						bookService.getAll());
			}
			case "-id": {
				return bookService.getById(Long.parseLong(filterValue)).toString();
//				return bookTableRenderer.render(
//						bookService.getById(Long.parseLong(filterValue)));
			}
			case "-genre": {

				return bookService.getByGenreName(filterValue).toString();

//				return bookTableRenderer.render(
//						bookService.getByGenreName(filterValue));
			}
			case "-author": {
				val fullNameArr = filterValue.split(" ");
//				return bookTableRenderer.render(
//						bookService.getByAuthorFirstNameAndLastName(fullNameArr[0], fullNameArr[1]));

				return bookService.getByAuthorFirstNameAndLastName(fullNameArr[0], fullNameArr[1]).toString();
			}
			default:
				return "Unsupported.\nUsing: book show [-id|-genre|-author <filterValue>]";
		}
	}

	@ShellMethod(value = "Add book", key = {"book add"}, group = "book")
	public String addBook() {
		val book = bookIOProvider.getBook();

		bookService.add(book);

		return book.toString();
	}


	@ShellMethod(value = "Delete book", key = {"book delete"}, group = "book", prefix = "-")
	public String deleteBook(@ShellOption(help = "Possible values are: '-id', '-title', '-author'", defaultValue = "-title") String filterName,
	                         String filterValue) {

		switch (filterName) {
			case "-title": {
				bookService.deleteByTitle(filterValue);
			}
			break;

			case "-id": {
				bookService.deleteById(Long.parseLong(filterValue));
			}
			break;

			case "-author": {
				val fullNameArr = filterValue.split(" ");
				bookService.deleteByAuthor(new Author(fullNameArr[0], fullNameArr[1]));
			}
			break;

			default:
				return "Unsupported.\nUsing: book delete [-id|-title|-author <filterValue>]";
		}
		return "Success";
	}

	@ShellMethod(value = "Update book", key = {"book update"}, group = "book", prefix = "-")
	public String updateBook(@NotBlank String id,
	                         @ShellOption(value = {"-t", "-title"}, defaultValue = "") String title,
	                         @ShellOption(value = {"-a", "-authors"}, defaultValue = "") String authors,
	                         @ShellOption(value = {"-g", "-genres"}, defaultValue = "") String genre) {

		ioService.outLine(bookService.getById(Long.parseLong(id)).toString());
		ioService.outLine("Please enter the books properties to update: ");
		ioService.outLine("example: \"$ title, genres\" for update books title and genres.");

		bookInteractiveUpdater.update();

		return "Success";
	}

}
