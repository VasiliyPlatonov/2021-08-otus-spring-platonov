package ru.vasiliyplatonov.homework7.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.vasiliyplatonov.homework7.domain.Book;
import ru.vasiliyplatonov.homework7.domain.BookComment;
import ru.vasiliyplatonov.homework7.service.BookCommentService;
import ru.vasiliyplatonov.homework7.service.BookService;
import ru.vasiliyplatonov.homework7.service.bookprovider.BookInteractiveProvider;
import ru.vasiliyplatonov.homework7.service.ioservice.IOService;
import ru.vasiliyplatonov.homework7.shell.table.renderer.TableRenderer;
import ru.vasiliyplatonov.homework7.shell.updater.BookInteractiveUpdater;

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
						bookService.findAll());
			}
			case "-id": {
				return bookTableRenderer.render(
						bookService.findById(Long.parseLong(filterValue)).orElse(null));
			}
			case "-genre": {
				return bookTableRenderer.render(
						bookService.findByGenreName(filterValue));
			}
			case "-author": {
				val fullNameArr = filterValue.split(" ");
				return bookTableRenderer.render(
						bookService.findByAuthorFirstNameAndLastName(fullNameArr[0], fullNameArr[1]));
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
	public String deleteBook(@NotNull @ShellOption(help = "Book`s id") Long id) {
		try {
			bookService.deleteById(id);
			return "Success";
		} catch (NoSuchElementException e) {
			return "There is no book comment with the id " + id;
		}
	}

	@ShellMethod(value = "Update book", key = {"book-update"}, group = "book", prefix = "-")
	public String updateBook(@NotNull @ShellOption(help = "Book`s id") Long id) {

		val bookOpt = bookService.findById(id);

		if (bookOpt.isPresent()) {
			val book = bookOpt.get();
			ioService.outLine(book.toString());
			val updatedBook = bookInteractiveUpdater.update(bookOpt.get());
			bookService.update(updatedBook);

			return "Success";
		} else {
			return "There is no book comment with the id " + id;
		}
	}


	@ShellMethod(value = "Show book`s comments", key = {"comment-show"}, group = "book comments", prefix = "-")
	public String showBookComments(@NotNull @ShellOption(help = "book`s id", value = {"-id", "-bookId"}) Long bookId,
	                               @ShellOption(help = "Maximum number of comments", value = "-n", defaultValue = "10") int max) {
		val comments = bookCommentService.findByBookId(bookId, max);
		comments.forEach(ioService::outLine);

		return "";
	}

	@ShellMethod(value = "Add book`s comments", key = {"comment-add"}, group = "book comments", prefix = "-")
	public String addBookComment(@NotNull @ShellOption(help = "book`s id", value = {"-id", "-bookId"}) Long bookId,
	                             @NotBlank @ShellOption(help = "text of comment") String text) {
		val bookOptional = bookService.findById(bookId);
		bookCommentService.add(new BookComment(text, bookOptional.orElseThrow()));

		return "Success";
	}

	@ShellMethod(value = "Delete book`s comment", key = {"comment-delete"}, group = "book comments", prefix = "-")
	public String deleteBookComment(@NotNull @ShellOption(help = "comment`s id") Long id) {
		try {
			bookCommentService.delete(id);
			return "Success";
		} catch (NoSuchElementException e) {
			return "There is no book comment with the id " + id;
		}
	}

	@ShellMethod(value = "Update book`s comment", key = {"comment-update"}, group = "book comments", prefix = "-")
	public String updateBookComment(@NotNull @ShellOption(help = "comment`s id") Long id, @NotBlank String text) {
		try {
			bookCommentService.update(id, text);
			return "Success";
		} catch (NoSuchElementException e) {
			return "There is no book comment with the id " + id;
		}

	}
}
