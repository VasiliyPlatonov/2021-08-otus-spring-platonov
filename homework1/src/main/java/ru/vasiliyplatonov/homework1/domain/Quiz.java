package ru.vasiliyplatonov.homework1.domain;

import lombok.Data;
import lombok.val;

import java.util.Collection;

@Data
public class Quiz {
    private final Collection<Question> questions;

    @Override
    public String toString() {
        val builder = new StringBuilder();

        builder
                .append("Quiz:\n\t")
                .append("questions:");

        questions.forEach(question -> {
            builder
                    .append("\n\t\t")
                    .append(question.getTitle());

            question.getPossibleAnswers().forEach(answer -> {
                if (!answer.getTitle().isBlank()) {
                    builder
                            .append("\n\t\t - ")
                            .append(answer.getTitle())
                            .append(answer.isCorrect() ? "(+)" : "");
                }
            });


        });

        return builder.toString();
    }
}
