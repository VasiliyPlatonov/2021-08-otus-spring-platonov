package ru.vasiliyplatonov.homework2.service.studentprovider;

import lombok.RequiredArgsConstructor;
import lombok.val;
import ru.vasiliyplatonov.homework2.domain.Student;

import java.util.Scanner;

@RequiredArgsConstructor
public class StdInStudentProvider implements StudentProvider {

	private final Scanner scanner;


	@Override
	public Student getStudent() {

		System.out.print("Enter your first name: ");
		val firstName = scanner.nextLine();

		System.out.print("Enter your last name: ");
		val lastName = scanner.nextLine();

		return new Student(firstName, lastName);
	}

}
