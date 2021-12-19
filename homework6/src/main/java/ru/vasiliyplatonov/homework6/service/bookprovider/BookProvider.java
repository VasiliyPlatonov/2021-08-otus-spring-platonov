package ru.vasiliyplatonov.homework6.service.bookprovider;

import ru.vasiliyplatonov.homework6.domain.Author;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.Genre;

import java.util.List;

public interface BookProvider {

	Book getBook();

	List<Genre> getGenres();

	List<Author> getAuthors();

	String getTitle();

}
