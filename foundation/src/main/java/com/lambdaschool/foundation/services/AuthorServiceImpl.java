package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Author;
import com.lambdaschool.foundation.models.Book;
import com.lambdaschool.foundation.models.Wrote;
import com.lambdaschool.foundation.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService
{

    @Autowired
    private AuthorRepository authorrepos;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserAuditing userAuditing;

    @Override
    public List<Author> findAll()
    {
        List<Author> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        authorrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Author save(Author author)
    {
        Author newAuthor = new Author();

        if (author.getAuthorid() != 0)
        {
            Author oldAuthor = authorrepos.findById(author.getAuthorid())
                .orElseThrow(() -> new EntityNotFoundException("Author id " + author.getAuthorid() + " not found!"));

            // delete the roles for the old user we are replacing
            for (Wrote w : oldAuthor.getBooks())
            {
                deleteWrote(w.getAuthor()
                        .getAuthorid(),
                    w.getBook()
                        .getBookid());
            }
            newAuthor.setAuthorid(author.getAuthorid());
        }

        newAuthor.setFirstname(author.getFirstname()
            .toLowerCase());
        newAuthor.setLastname(author.getLastname()
            .toLowerCase());

        newAuthor.getBooks()
            .clear();
        if (author.getAuthorid() == 0)
        {
            for (Wrote w : author.getBooks())
            {
                Book newBook = bookService.findById(w.getBook()
                    .getBookid());

                newAuthor.addBook(newBook);
            }
        } else
        {
            // add the new roles for the user we are replacing
            for (Wrote w : author.getBooks())
            {
                addWrote(newAuthor.getAuthorid(),
                    w.getBook()
                        .getBookid());
            }
        }

//        newAuthor.getUseremails()
//            .clear();
//        for (Useremail ue : user.getUseremails())
//        {
//            newUser.getUseremails()
//                .add(new Useremail(newUser,
//                    ue.getUseremail()));
//        }

        return authorrepos.save(newAuthor);
    }

    @Transactional
    @Override
    public void deleteWrote(
        long authorid,
        long bookid)
    {
        authorrepos.findById(authorid)
            .orElseThrow(() -> new EntityNotFoundException("Author id " + authorid + " not found!"));
        bookService.findById(bookid);

        if (authorrepos.checkWroteCombo(authorid,
            bookid)
            .getCount() > 0)
        {
            authorrepos.deleteWrote(authorid,
                bookid);
        } else
        {
            throw new EntityNotFoundException("Author and Book Combination Does Not Exists");
        }
    }

    @Transactional
    @Override
    public void addWrote(
        long authorid,
        long bookid)
    {
        authorrepos.findById(authorid)
            .orElseThrow(() -> new EntityNotFoundException("Author id " + authorid + " not found!"));
        bookService.findById(bookid);

        if (authorrepos.checkWroteCombo(authorid,
            bookid)
            .getCount() <= 0)
        {
            authorrepos.insertUserRoles(userAuditing.getCurrentAuditor()
                    .get(),
                authorid,
                bookid);
        } else
        {
            throw new EntityExistsException("Author and Book Combination Already Exists");
        }
    }
}
