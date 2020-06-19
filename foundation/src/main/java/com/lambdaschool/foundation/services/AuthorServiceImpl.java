package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Override
    public List<Author> findAll()
    {
        return null;
    }
}
