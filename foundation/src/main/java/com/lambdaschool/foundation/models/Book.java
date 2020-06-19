package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    @NotNull
    @Column(nullable = false)
    private String booktitle;

    @NotNull
    @Column(nullable = false, unique = true)
    private String ISBN;

    @Column(nullable = true)
    private int copy;

    @OneToMany(mappedBy = "book",
        cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "book",
        allowSetters = true)
    private List<AuthorBooks> authors = new ArrayList<>();

    public Book()
    {
    }

    public Book(
        @NotNull String booktitle,
        @NotNull String ISBN,
        int copy,
        List<AuthorBooks> authors)
    {
        this.booktitle = booktitle;
        this.ISBN = ISBN;
        this.copy = copy;
        this.authors = authors;
    }

    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getBooktitle()
    {
        return booktitle;
    }

    public void setBooktitle(String booktitle)
    {
        this.booktitle = booktitle;
    }

    public String getISBN()
    {
        return ISBN;
    }

    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }

    public int getCopy()
    {
        return copy;
    }

    public void setCopy(int copy)
    {
        this.copy = copy;
    }

    public List<AuthorBooks> getAuthors()
    {
        return authors;
    }

    public void setAuthors(List<AuthorBooks> authors)
    {
        this.authors = authors;
    }
}
