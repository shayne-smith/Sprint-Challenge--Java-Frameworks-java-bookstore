package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    @NotNull
    @Column(nullable = false)
    private String lastname;

    @NotNull
    @Column(nullable = false)
    private String firstname;

    @OneToMany(mappedBy = "author",
        cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "author",
        allowSetters = true)
    private List<Wrote> wrotes = new ArrayList<>();

    public Author()
    {
    }

    public Author(
        @NotNull String lastname,
        @NotNull String firstname)
    {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Author(
        @NotNull String lastname,
        @NotNull String firstname,
        List<Wrote> wrotes)
    {
        this.lastname = lastname;
        this.firstname = firstname;
        this.wrotes = wrotes;
    }

    public long getAuthorid()
    {
        return authorid;
    }

    public void setAuthorid(long authorid)
    {
        this.authorid = authorid;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public List<Wrote> getWrotes()
    {
        return wrotes;
    }

    public void setBooks(List<Wrote> wrotes)
    {
        this.wrotes = wrotes;
    }

    public void addBook(Book book)
    {
        wrotes.add(new Wrote(this,
            book));
    }
}
