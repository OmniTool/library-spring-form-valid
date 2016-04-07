package net.library.spring.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors", schema = "public", catalog = "library_test")
public class Author extends EntityBase {

    @Basic @Column(name = "second_name")
    private String secondName;
    @Basic @Column(name = "first_name")
    private String firstName;
    @Basic @Column(name = "middle_name")
    private String middleName;
    @Basic @Column(name = "birth_year")
    private Integer birthYear;
    @Basic @Column(name = "biography")
    private String biography;
    @OneToMany(targetEntity=BookAuthor.class, mappedBy = "author", fetch = FetchType.EAGER, orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<BookAuthor> booksList = new ArrayList<>();

    public Author() {}

    public List<BookAuthor> getBooksList() {
        return booksList;
    }
    public void setBooksList(List<BookAuthor> booksList) {
        this.booksList = booksList;
    }
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public Integer getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }
    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
}
