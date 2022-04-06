package ru.vasiliyplatonov.homework7.shell.table.mapper;

import org.springframework.shell.table.Table;

import java.util.List;

public interface TableMapper<T> {
    Table mapToTable(List<T> objects);

    Table mapToTable(T object);
}
