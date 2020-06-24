package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Author;
import com.lambdaschool.foundation.models.Book;
import com.lambdaschool.foundation.repository.AuthorRepository;
import com.lambdaschool.foundation.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookrepos;

    @Autowired
    private AuthorRepository authorrepos;

    @Override
    public List<Book> findAll()
    {
        List<Book> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        bookrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        bookrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Book id " + id + " not found!"));
        bookrepos.deleteById(id);

        // delete book author combos too
    }

    @Transactional
    @Override
    public Useremail save(
        long userid,
        String emailaddress)
    {
        User currentUser = userService.findUserById(userid);

        Useremail newUserEmail = new Useremail(currentUser,
            emailaddress);
        return useremailrepos.save(newUserEmail);
    }
}