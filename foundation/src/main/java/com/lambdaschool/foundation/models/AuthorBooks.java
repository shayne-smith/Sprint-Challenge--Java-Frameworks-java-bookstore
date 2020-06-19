package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "authorbooks",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"authorid", "bookid"})})
public class AuthorBooks extends Auditable implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "authorid")
    @JsonIgnoreProperties(value = "books",
        allowSetters = true)
    private Author author;

    @Id
    @ManyToOne
    @JoinColumn(name = "bookid")
    @JsonIgnoreProperties(value = "authors",
        allowSetters = true)
    private Book book;

    public AuthorBooks()
    {
    }

    public AuthorBooks(
        Author author,
        Book book)
    {
        this.author = author;
        this.book = book;
    }

    public Author getAuthor()
    {
        return author;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getAuthor(), getBook());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof AuthorBooks))
        {
            return false;
        }
        AuthorBooks that = (AuthorBooks) o;
        return Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getBook(), that.getBook());
    }
}
