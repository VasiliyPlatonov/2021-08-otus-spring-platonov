package ru.vasiliyplatonov.backend.service.ioservice;

public interface IOService {
    void out(String message);

    void outLine(Object message);

    String readLine();
}
