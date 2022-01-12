package ru.vasiliyplatonov.homework5.shell.table.mapper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.table.*;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework5.domain.Author;
import ru.vasiliyplatonov.homework5.domain.Book;
import ru.vasiliyplatonov.homework5.domain.Genre;
import ru.vasiliyplatonov.homework5.shell.table.formatter.AuthorFormatter;
import ru.vasiliyplatonov.homework5.shell.table.formatter.GenreFormatter;

import java.util.LinkedHashMap;
import java.util.List;

@Service("bookTableMapper")
@RequiredArgsConstructor
public class BookTableMapper implements TableMapper<Book> {

    private final AuthorFormatter authorFormatter;
    private final GenreFormatter genreFormatter;

    @Override
    public Table mapToTable(List<Book> books) {

        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
        headers.put("id", "Id");
        headers.put("title", "Title");
        headers.put("author", "Author");
        headers.put("genre", "Genre");

        return buildTable(new BeanListTableModel<>(books, headers));
    }

    @Override
    public Table mapToTable(Book book) {
        val bookData = new Object[][]{
                {"Id", String.valueOf(book.getId())},
                {"Title", book.getTitle()},
                {"Author", book.getAuthor()},
                {"Genre", book.getGenre()},
        };

        return buildTable(new ArrayTableModel(bookData));
    }

    private Table buildTable(TableModel tableModel) {
        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.ofType(Author.class)).addFormatter(authorFormatter);
        tableBuilder.on(CellMatchers.ofType(Genre.class)).addFormatter(genreFormatter);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);

        return tableBuilder.build();
    }
}
