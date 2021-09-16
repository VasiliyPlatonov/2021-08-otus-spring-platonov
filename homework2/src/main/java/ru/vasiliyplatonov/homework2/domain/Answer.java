package ru.vasiliyplatonov.homework2.domain;

import lombok.Data;

@Data
public class Answer {
    private final String title;
    private final boolean isCorrect;

    @Override
    public String toString() {
        return title.length() > 0 ? " \n\t- " + title + (isCorrect ? "(+)" : "") : "\n\t ---";
    }
}
