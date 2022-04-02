package ru.vasiliyplatonov.backend.service.bookprovider;

import ru.vasiliyplatonov.backend.domain.Author;
import ru.vasiliyplatonov.backend.domain.Book;
import ru.vasiliyplatonov.backend.domain.Genre;

import java.util.Set;

public interface BookProvider {

	Book getBook();

	Set<Genre> getGenres();

	Set<Author> getAuthors();

	String getTitle();

}
