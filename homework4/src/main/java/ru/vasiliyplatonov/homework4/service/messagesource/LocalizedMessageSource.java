package ru.vasiliyplatonov.homework4.service.messagesource;

public interface LocalizedMessageSource {
	String getMessage(String code);

	String getMessage(String code, Object[] args);
}
