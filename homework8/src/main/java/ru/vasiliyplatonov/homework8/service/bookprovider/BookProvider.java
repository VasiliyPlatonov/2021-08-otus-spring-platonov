package ru.vasiliyplatonov.homework8.service.bookprovider;

import ru.vasiliyplatonov.homework8.domain.Author;
import ru.vasiliyplatonov.homework8.domain.Book;
import ru.vasiliyplatonov.homework8.domain.Genre;

import java.util.Set;

public interface BookProvider {

	Book getBook();

	Set<Genre> getGenres();

	Set<Author> getAuthors();

	String getTitle();

}
