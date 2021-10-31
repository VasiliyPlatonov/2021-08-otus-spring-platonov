package ru.vasiliyplatonov.homework5.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework5.dao.BookDao;
import ru.vasiliyplatonov.homework5.domain.Author;
import ru.vasiliyplatonov.homework5.domain.Book;
import ru.vasiliyplatonov.homework5.domain.Genre;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao dao;

    @Override
    public Book add(Book book) {
        val id = dao.add(book);
        return new Book(id, book.getTitle(), book.getAuthor(), book.getGenre());
    }

    @Override
    public Book getById(long id) {
        try {
            return dao.getById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Override
    public List<Book> getByTitle(String title) {
        return dao.getByTitle(title);
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        return dao.getByAuthor(author);
    }

    @Override
    public List<Book> getByAuthor(String author) {

        return getAll().stream()
                .filter(book -> book.getAuthor().getFirstName().equalsIgnoreCase(author) ||
                        book.getAuthor().getLastName().equalsIgnoreCase(author)
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        return getAll().stream()
                .filter(book -> book.getGenre().equals(genre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getByGenre(String genre) {
        return getAll().stream()
                .filter(book -> book.getGenre().getName().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Book book) {
        dao.update(book);
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }
}