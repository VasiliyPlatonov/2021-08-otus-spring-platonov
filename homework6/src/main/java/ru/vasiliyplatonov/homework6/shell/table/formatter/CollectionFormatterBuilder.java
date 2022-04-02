package ru.vasiliyplatonov.homework6.shell.table.formatter;

import lombok.val;
import org.springframework.shell.table.Formatter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CollectionFormatterBuilder {

	public <T> Formatter build(Function<T, String> mapFunction) {
		return (Object value) -> {

			@SuppressWarnings("unchecked")
			val collection = (Collection<T>) value;

			return collection.stream()
					.map(mapFunction)
					.collect(Collectors.toList())
					.toArray(new String[collection.size()]);
		};
	}

}
