package org.yyf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Book> getAllBooks() {
        return null;
    }

    @RequestMapping(value = "/{isbn}", method =
            RequestMethod.GET)
    public Book getBook(@PathVariable String isbn) {
        return null;
    }
}