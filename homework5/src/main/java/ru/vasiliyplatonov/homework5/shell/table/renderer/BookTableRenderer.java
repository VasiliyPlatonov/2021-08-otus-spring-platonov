package ru.vasiliyplatonov.homework5.shell.table.renderer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework5.domain.Book;
import ru.vasiliyplatonov.homework5.shell.table.mapper.TableMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookTableRenderer implements TableRenderer<Book> {

    private final static int TABLE_WIDTH = 120;
    private final TableMapper<Book> bookTableMapper;


    @Override
    public String render(List<Book> books) {
        if (books.isEmpty()) {
            return "No books found";
        }
        return bookTableMapper.mapToTable(books).render(TABLE_WIDTH);
    }

    @Override
    public String render(Book book) {
        if (book == null) {
            return "No books found";
        }
        return bookTableMapper.mapToTable(book).render(TABLE_WIDTH);
    }
}
