package ru.vasiliyplatonov.homework5.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

	@EqualsAndHashCode.Include
	private final long id;
	private final String title;
	private final Author author;
	private final Genre genre;
}
