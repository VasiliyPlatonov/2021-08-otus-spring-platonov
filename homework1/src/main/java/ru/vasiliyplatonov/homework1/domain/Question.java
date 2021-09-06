package ru.vasiliyplatonov.homework1.domain;

import lombok.Data;

import java.util.Collection;

@Data
public class Question {
    private final String title;
    private final Collection<Answer> possibleAnswers;

    @Override
    public String toString() {
        return "\n" + title + possibleAnswers;
    }
}
