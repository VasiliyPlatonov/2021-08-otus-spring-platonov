package ru.vasiliyplatonov.homework5.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Author {

	@EqualsAndHashCode.Include
	private final long id;
	private final String firstName;
	private final String lastName;

}
