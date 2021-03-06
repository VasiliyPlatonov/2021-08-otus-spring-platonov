package ru.vasiliyplatonov.homework6.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.BookComment;
import ru.vasiliyplatonov.homework6.service.BookCommentService;
import ru.vasiliyplatonov.homework6.service.BookService;
import ru.vasiliyplatonov.homework6.service.bookprovider.BookInteractiveProvider;
import ru.vasiliyplatonov.homework6.service.ioservice.IOService;
import ru.vasiliyplatonov.homework6.shell.table.renderer.TableRenderer;
import ru.vasiliyplatonov.homework6.shell.updater.BookInteractiveUpdater;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

	private final BookService bookService;
	private final BookCommentService bookCommentService;
	private final TableRenderer<Book> bookTableRenderer;
	private final BookInteractiveProvider bookInteractiveProvider;
	private final IOService ioService;
	private final BookInteractiveUpdater bookInteractiveUpdater;


	@ShellMethod(value = "Show book", key = {"book-show"}, group = "book", prefix = "-")
	public String showBook(@ShellOption(help = "Possible values are: '-id', '-genre', '-author'", defaultValue = "all") String filterName,
	                       @ShellOption(defaultValue = "") String filterValue) {

		switch (filterName) {
			case "all": {
				return bookTableRenderer.render(
						bookService.getAll());
			}
			case "-id": {
				return bookTableRenderer.render(
						bookService.getById(Long.parseLong(filterValue)));
			}
			case "-genre": {
				return bookTableRenderer.render(
						bookService.getByGenreName(filterValue));
			}
			case "-author": {
				val fullNameArr = filterValue.split(" ");
				return bookTableRenderer.render(
						bookService.getByAuthorFirstNameAndLastName(fullNameArr[0], fullNameArr[1]));
			}
			default:
				return "Unsupported.\nUsing: book show [-id|-genre|-author <filterValue>]";
		}
	}

	@ShellMethod(value = "Add book", key = {"book-add"}, group = "book")
	public String addBook() {
		val book = bookInteractiveProvider.getBook();

		bookService.add(book);

		return book.toString();
	}


	@ShellMethod(value = "Delete book", key = {"book-delete"}, group = "book", prefix = "-")
	public String deleteBook(@ShellOption(help = "Possible values are: '-id'", defaultValue = "-id") Long id) {
		try {
			bookService.deleteById(id);
			return "Success";
		} catch (NoSuchElementException e) {
			return "There is no book comment with the id " + id;
		}
	}

	@ShellMethod(value = "Update book", key = {"book-update"}, group = "book", prefix = "-")
	public String updateBook(@NotBlank Long id) {

		val book = bookService.getById(id);
		ioService.outLine(book.toString());

		val updatedBook = bookInteractiveUpdater.update(book);

		bookService.update(updatedBook);

		return "Success";
	}


	@ShellMethod(value = "Show book`s comments", key = {"comment-show"}, group = "book comments", prefix = "-")
	public String showBookComments(@NotNull @ShellOption(help = "book`s id", value = {"-id", "-bookId"}) Long bookId) {

		val comments = bookService.getBookCommentsByBookId(bookId);
		comments.forEach(ioService::outLine);

		return "";
	}

	@ShellMethod(value = "Add book`s comments", key = {"comment-add"}, group = "book comments", prefix = "-")
	public String addBookComments(@NotNull @ShellOption(help = "book`s id", value = {"-id", "-bookId"}) Long bookId,
	                              @NotBlank @ShellOption(help = "text of comment") String text) {
		val book = bookService.getById(bookId);
		bookCommentService.add(new BookComment(text, book));

		return "Success";
	}

	@ShellMethod(value = "Delete book`s comment", key = {"comment-delete"}, group = "book comments", prefix = "-")
	public String deleteBookComments(@NotNull @ShellOption(help = "comment`s id") Long id) {
		try {
			bookCommentService.delete(id);
			return "Success";
		} catch (NoSuchElementException e) {
			return "There is no book comment with the id " + id;
		}
	}

	@ShellMethod(value = "Update book`s comment", key = {"comment-update"}, group = "book comments", prefix = "-")
	public String updateBookComments(@NotNull @ShellOption(help = "comment`s id") Long id, @NotBlank String text) {
		try {
			bookCommentService.update(id, text);
			return "Success";
		} catch (NoSuchElementException e) {
			return "There is no book comment with the id " + id;
		}

	}
}
