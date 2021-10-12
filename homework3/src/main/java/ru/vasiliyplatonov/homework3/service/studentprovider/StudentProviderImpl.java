package ru.vasiliyplatonov.homework3.service.studentprovider;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.vasiliyplatonov.homework3.domain.Student;
import ru.vasiliyplatonov.homework3.service.ioservice.IOService;
import ru.vasiliyplatonov.homework3.service.messagesource.LocalizedMessageSource;

@Service
@RequiredArgsConstructor
public class StudentProviderImpl implements StudentProvider {

	private final IOService ioService;
	private final LocalizedMessageSource msgSource;

	@Override
	public Student getStudent() {
		ioService.out(msgSource.getMessage("enter-first-name") + ": ");
		val firstName = ioService.readLine();

		ioService.out(msgSource.getMessage("enter-last-name") + ": ");
		val lastName = ioService.readLine();

		return new Student(firstName, lastName);
	}

}
