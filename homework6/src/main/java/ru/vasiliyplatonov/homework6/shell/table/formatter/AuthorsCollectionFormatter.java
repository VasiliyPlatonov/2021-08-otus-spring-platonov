package ru.vasiliyplatonov.homework6.shell.table.formatter;

import lombok.val;
import org.springframework.shell.table.Formatter;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework6.domain.Author;

import java.util.Collection;

@Service
public class AuthorsCollectionFormatter implements Formatter {

	@Override
	public String[] format(Object value) {
		System.out.println(value);

		val authors = (Collection<Author>) value;

		return (String[]) (authors.stream()
				.map(author -> author.getFirstName() + " " + author.getLastName())
				.toArray());
	}
}
