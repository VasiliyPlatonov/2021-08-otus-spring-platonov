package ru.vasiliyplatonov.homework3.service.messagesource;

import org.springframework.context.MessageSource;

import java.util.Locale;


public final class LocalizedMessageSourceImpl implements LocalizedMessageSource {

	private final Locale locale;
	private final MessageSource messageSource;

	public LocalizedMessageSourceImpl(Locale locale, MessageSource messageSource) {
		this.locale = locale;
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
