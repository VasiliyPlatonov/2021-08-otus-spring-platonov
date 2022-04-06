package ru.vasiliyplatonov.homework8.shell.table.formatter;

import lombok.val;
import org.springframework.shell.table.Formatter;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework8.domain.Author;

@Service
public class AuthorFormatter implements Formatter {
    @Override
    public String[] format(Object author) {
        val firstName = ((Author) author).getFirstName();
        val lastName = ((Author) author).getLastName();

        return new String[]{firstName, lastName};
    }


}
