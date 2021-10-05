package ru.vasiliyplatonov.homework3.service.studentprovider;

import lombok.RequiredArgsConstructor;
import lombok.val;
import ru.vasiliyplatonov.homework3.domain.Student;
import ru.vasiliyplatonov.homework3.service.i18n.LocalizedMessageSource;

import java.util.Scanner;

@RequiredArgsConstructor
public class StdInStudentProvider implements StudentProvider {

	private final Scanner scanner;
	private final LocalizedMessageSource msgSource;

	@Override
	public Student getStudent() {
		System.out.print(msgSource.getMessage("enter-first-name") + ": ");
		val firstName = scanner.nextLine();

		System.out.print(msgSource.getMessage("enter-last-name") + ": ");
		val lastName = scanner.nextLine();

		return new Student(firstName, lastName);
	}

}
