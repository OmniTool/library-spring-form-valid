package net.library.spring.entities;

import javax.persistence.*;
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
    @OneToMany(targetEntity=BookAuthor.class, mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        if (!super.equals(o)) return false;

        Author author = (Author) o;

        if (getSecondName() != null ? !getSecondName().equals(author.getSecondName()) : author.getSecondName() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(author.getFirstName()) : author.getFirstName() != null)
            return false;
        if (getMiddleName() != null ? !getMiddleName().equals(author.getMiddleName()) : author.getMiddleName() != null)
            return false;
        if (getBirthYear() != null ? !getBirthYear().equals(author.getBirthYear()) : author.getBirthYear() != null)
            return false;
        if (getBiography() != null ? !getBiography().equals(author.getBiography()) : author.getBiography() != null)
            return false;
        return !(getBooksList() != null ? !getBooksList().equals(author.getBooksList()) : author.getBooksList() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getSecondName() != null ? getSecondName().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getMiddleName() != null ? getMiddleName().hashCode() : 0);
        result = 31 * result + (getBirthYear() != null ? getBirthYear().hashCode() : 0);
        result = 31 * result + (getBiography() != null ? getBiography().hashCode() : 0);
        result = 31 * result + (getBooksList() != null ? getBooksList().hashCode() : 0);
        return result;
    }
}
