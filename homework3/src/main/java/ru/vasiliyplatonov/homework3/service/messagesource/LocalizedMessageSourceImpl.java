package ru.vasiliyplatonov.homework3.service.messagesource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public final class LocalizedMessageSourceImpl implements LocalizedMessageSource {

	private final Locale locale;
	private final MessageSource messageSource;

	public LocalizedMessageSourceImpl(@Value("${application.language-tag}") String languageTag, MessageSource messageSource) {
		this.locale = Locale.forLanguageTag(languageTag);
		this.messageSource = messageSource;
	}

	@Override
	public String getMessage(String code) {
		return messageSource.getMessage(code, null, locale);
	}

	@Override
	public String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, locale);
	}

}
