package ru.vasiliyplatonov.homework8.shell.table.mapper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.table.*;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework8.domain.Author;
import ru.vasiliyplatonov.homework8.domain.Book;
import ru.vasiliyplatonov.homework8.domain.Genre;
import ru.vasiliyplatonov.homework8.shell.table.formatter.CollectionFormatterBuilder;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

@Service("bookTableMapper")
@RequiredArgsConstructor
public class BookTableMapper implements TableMapper<Book> {

//    private final AuthorsCollectionFormatter authorsFormatter;
//    private final GenresCollectionFormatter genresFormatter;

	private final CollectionFormatterBuilder collectionFormatterBuilder;


	@Override
	public Table mapToTable(List<Book> books) {

		LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
		headers.put("id", "Id");
		headers.put("title", "Title");
		headers.put("authors", "Authors");
		headers.put("genres", "Genres");

		return buildTable(new BeanListTableModel<>(books, headers));
	}

	@Override
	public Table mapToTable(Book book) {
		val bookData = new Object[][]{
				{"Id", String.valueOf(book.getId())},
				{"Title", book.getTitle()},
				{"Authors", book.getAuthors()},
				{"Genres", book.getGenres()},
		};

		return buildTable(new ArrayTableModel(bookData));
	}

	private Table buildTable(TableModel tableModel) {
		val tableBuilder = new TableBuilder(tableModel);

		val authorsFormatter = collectionFormatterBuilder.build(
				author -> ((Author) author).getFirstName() + " " + ((Author) author).getLastName() + "\n");

		val genresFormatter = collectionFormatterBuilder.build(
				o -> ((Genre) o).getName() + "\n"
		);

		tableBuilder.on(getTypedCollectionMatcher(Genre.class)).addFormatter(genresFormatter);
		tableBuilder.on(getTypedCollectionMatcher(Author.class)).addFormatter(authorsFormatter);

		tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
		tableBuilder.addInnerBorder(BorderStyle.fancy_light);

		return tableBuilder.build();
	}


	private static CellMatcher getTypedCollectionMatcher(Class<?> clazz) {
		return (row, column, model) -> {
			val value = model.getValue(row, column);
			val isCollection = Collection.class.isAssignableFrom(value.getClass());

			if (isCollection) {
				val iterator = ((Collection<?>) value).iterator();
				if (iterator.hasNext()) {
					val collectionItem = iterator.next();
					return clazz.isAssignableFrom(collectionItem.getClass());
				}
			}
			return false;
		};
	}

}
