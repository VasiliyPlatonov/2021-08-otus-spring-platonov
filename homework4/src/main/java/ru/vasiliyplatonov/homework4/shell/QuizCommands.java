package ru.vasiliyplatonov.homework4.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.vasiliyplatonov.homework4.controller.QuizController;

@ShellComponent
@RequiredArgsConstructor
public class QuizCommands {

	private final QuizController quizController;

	@ShellMethod(value = "Start quiz", key = {"start","start-quiz", "conduct-quiz"})
	public void startQuiz() {
		quizController.conductQuiz();
	}
}
