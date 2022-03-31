package ru.vasiliyplatonov.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vasiliyplatonov.backend.domain.Book;
import ru.vasiliyplatonov.backend.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public List<Book> getBooks() {
        log.info("getBooks");

        return bookService.findAll();

    }

}
