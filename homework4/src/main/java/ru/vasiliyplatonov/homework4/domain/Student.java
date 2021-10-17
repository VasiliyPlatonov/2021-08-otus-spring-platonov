package ru.vasiliyplatonov.homework4.domain;

import lombok.Data;

@Data
public class Student {
	private final String firstName;
	private final String lastName;
	private int score;

	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
