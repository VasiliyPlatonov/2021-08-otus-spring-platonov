package ru.vasiliyplatonov.homework7.shell.table.formatter;

import lombok.val;
import org.springframework.shell.table.Formatter;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework7.domain.Genre;

@Service
public class GenreFormatter implements Formatter {
    @Override
    public String[] format(Object genre) {
        val genreName = ((Genre) genre).getName();
        return new String[]{genreName};
    }
}
