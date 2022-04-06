package ru.vasiliyplatonov.homework8.shell.table.formatter;

import lombok.val;
import org.springframework.shell.table.Formatter;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework8.domain.Genre;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class GenresCollectionFormatter implements Formatter {

	@Override
	public String[] format(Object value) {
		@SuppressWarnings("unchecked")
		val genres = (Collection<Genre>) value;

		return genres.stream()
				.map(Genre::getName)
				.collect(Collectors.toList())
				.toArray(new String[genres.size()]);
	}

}
