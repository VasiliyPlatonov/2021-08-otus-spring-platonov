package ru.vasiliyplatonov.homework5.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class LibraryPromptProvider implements PromptProvider {

	@Override
	public AttributedString getPrompt() {
		return new AttributedString("library:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
	}

}
