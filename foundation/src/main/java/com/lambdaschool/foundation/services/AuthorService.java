package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Author;

import java.util.List;

public interface AuthorService
{
    List<Author> findAll();

    Author save(Author author);

    void deleteWrote(long authorid, long bookid);

    void addWrote(
        long authorid,
        long bookid);
}
