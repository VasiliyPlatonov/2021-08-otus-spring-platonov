package ru.vasiliyplatonov.homework8.service.ioservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {
    private final PrintStream out;
    private final Scanner scanner;

    public IOServiceImpl(@Value("#{T(java.lang.System).in}") InputStream in,
                         @Value("#{T(java.lang.System).out}") PrintStream out) {
        this.out = out;
        this.scanner = new Scanner(in);
    }

    @Override
    public void out(String message) {
        out.print(message);
    }

    @Override
    public void outLine(Object obj) {
        out.println(obj.toString());
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
