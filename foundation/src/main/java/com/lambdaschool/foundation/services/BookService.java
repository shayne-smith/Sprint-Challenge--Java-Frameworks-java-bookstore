package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Book;

import java.util.List;

public interface BookService
{
    List<Book> findAll();

    void delete(long id);

    Book findById(long id);

    Book save(Book book);
}
