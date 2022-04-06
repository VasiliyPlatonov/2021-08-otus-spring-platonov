package ru.vasiliyplatonov.homework7.service.ioservice;

public interface IOService {
    void out(String message);

    void outLine(Object message);

    String readLine();
}
