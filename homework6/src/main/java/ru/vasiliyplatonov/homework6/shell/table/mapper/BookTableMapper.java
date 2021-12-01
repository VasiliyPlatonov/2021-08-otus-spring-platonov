package ru.vasiliyplatonov.homework6.shell.table.mapper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.table.*;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.shell.table.formatter.AuthorFormatter;
import ru.vasiliyplatonov.homework6.shell.table.formatter.AuthorsCollectionFormatter;
import ru.vasiliyplatonov.homework6.shell.table.formatter.GenreFormatter;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

@Service("bookTableMapper")
@RequiredArgsConstructor
public class BookTableMapper implements TableMapper<Book> {

    private final AuthorFormatter authorFormatter;
    private final GenreFormatter genreFormatter;
    private final AuthorsCollectionFormatter authorsFormatter;

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
//                {"Genres", book.getGenres()},
        };

        return buildTable(new ArrayTableModel(bookData));
    }

    private Table buildTable(TableModel tableModel) {
        TableBuilder tableBuilder = new TableBuilder(tableModel);
//        tableBuilder.on(CellMatchers.ofType(Author.class)).addFormatter(authorFormatter);
//        tableBuilder.on(CellMatchers.ofType(Genre.class)).addFormatter(genreFormatter);
        tableBuilder.on(CellMatchers.ofType(Collection.class)).addFormatter(authorsFormatter);
//        tableBuilder.on(CellMatchers.column(4)).addFormatter(genreFormatter);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);

        return tableBuilder.build();
    }
}
