package ru.vasiliyplatonov.homework6.service.bookprovider;

import ru.vasiliyplatonov.homework6.domain.Author;
import ru.vasiliyplatonov.homework6.domain.Book;
import ru.vasiliyplatonov.homework6.domain.Genre;

import java.util.Set;

public interface BookProvider {

	Book getBook();

	Set<Genre> getGenres();

	Set<Author> getAuthors();

	String getTitle();

}
