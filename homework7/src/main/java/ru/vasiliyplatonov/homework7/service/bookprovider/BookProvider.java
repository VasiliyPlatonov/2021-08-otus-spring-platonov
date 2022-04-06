package ru.vasiliyplatonov.homework7.service.bookprovider;

import ru.vasiliyplatonov.homework7.domain.Author;
import ru.vasiliyplatonov.homework7.domain.Book;
import ru.vasiliyplatonov.homework7.domain.Genre;

import java.util.Set;

public interface BookProvider {

	Book getBook();

	Set<Genre> getGenres();

	Set<Author> getAuthors();

	String getTitle();

}
