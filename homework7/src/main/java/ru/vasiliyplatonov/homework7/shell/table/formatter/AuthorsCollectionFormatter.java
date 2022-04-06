package ru.vasiliyplatonov.homework7.shell.table.formatter;

import lombok.val;
import org.springframework.shell.table.Formatter;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework7.domain.Author;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AuthorsCollectionFormatter implements Formatter {

	@Override
	public String[] format(Object value) {
		@SuppressWarnings("unchecked")
		val authors = (Collection<Author>) value;

		return authors.stream()
				.map(author -> (author.getFirstName() + " " + author.getLastName()))
				.collect(Collectors.toList())
				.toArray(new String[authors.size()]);

	}
}
