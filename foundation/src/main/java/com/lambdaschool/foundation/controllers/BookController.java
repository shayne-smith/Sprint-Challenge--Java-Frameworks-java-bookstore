package com.lambdaschool.foundation.controllers;

import com.lambdaschool.foundation.models.Author;
import com.lambdaschool.foundation.models.Book;
import com.lambdaschool.foundation.services.AuthorService;
import com.lambdaschool.foundation.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController
{
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books",
        produces = {"application/json"})
    public ResponseEntity<?> listAllBooks()
    {
        List<Book> myBooks = bookService.findAll();
        return new ResponseEntity<>(myBooks,
            HttpStatus.OK);
    }


}
