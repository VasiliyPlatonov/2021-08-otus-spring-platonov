package ru.vasiliyplatonov.homework4.domain;

import lombok.Data;

@Data
public class Answer {
	private final String name;
	private final String title;
	private final boolean isCorrect;

	@Override
	public String toString() {
		return name + ") " + title;
	}
}
