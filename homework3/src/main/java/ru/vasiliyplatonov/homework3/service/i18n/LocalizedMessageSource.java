package ru.vasiliyplatonov.homework3.service.i18n;

public interface LocalizedMessageSource {
	String getMessage(String code);

	String getMessage(String code, Object[] args);
}
