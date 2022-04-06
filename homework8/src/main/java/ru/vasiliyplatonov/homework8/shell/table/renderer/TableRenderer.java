package ru.vasiliyplatonov.homework8.shell.table.renderer;

import java.util.List;

public interface TableRenderer<T> {
    String render(List<T> objects);

    String render(T object);
}
