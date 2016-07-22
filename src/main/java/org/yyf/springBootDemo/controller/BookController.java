package org.yyf.springBootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RestController
@RequestMapping("/books")
public class BookController {
    @RequestMapping(value = "d", method = RequestMethod.GET)
    public Iterable<Book> getAllBooks() {
        return null;
    }

    @RequestMapping(value = "/{isbn}", method =
            RequestMethod.GET)
    public Book getBook(@PathVariable String isbn) {
        return null;
    }
    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return "hello,spring boot";
    }
}