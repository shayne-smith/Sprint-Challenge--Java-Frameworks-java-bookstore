package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sections")
public class Section extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sectionid;

    @NotNull
    @Column(nullable = false,
        unique = true)
    private String sectionname;

    @OneToMany(mappedBy = "section",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "sections",
        allowSetters = true)
    private List<Book> books = new ArrayList<>();

    public Section()
    {
    }

    public Section(@NotNull String sectionname)
    {
        this.sectionname = sectionname;
    }

    public Section(
        @NotNull String sectionname,
        List<Book> books)
    {
        this.sectionname = sectionname;
        this.books = books;
    }

    public long getSectionid()
    {
        return sectionid;
    }

    public void setSectionid(long sectionid)
    {
        this.sectionid = sectionid;
    }

    public String getSectionname()
    {
        return sectionname;
    }

    public void setSectionname(String sectionname)
    {
        this.sectionname = sectionname;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }
}
