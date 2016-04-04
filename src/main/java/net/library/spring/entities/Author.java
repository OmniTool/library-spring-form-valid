package net.library.spring.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors", schema = "public", catalog = "library_test")
public class Author extends EntityBase {

    @Basic @Column(name = "second_name")
    @Size(min=1, max=64, message="Фамилия: от 1 до 64 символов")
    private String secondName;
    @Basic @Column(name = "first_name")
    @Size(min=1, max=64, message="Имя: от 1 до 64 символов")
    private String firstName;
    @Basic @Column(name = "middle_name")
    @Size(min=1, max=64, message="Отчество: от 1 до 64 символов")
    private String middleName;
    @Basic @Column(name = "birth_year")
//    @Pattern(regexp = "-?\\d{4}", message = "Год: 4 цифры (отрицательное значение для года до н.э.)")
    private Integer birthYear;
    @Basic @Column(name = "biography")
    @Size(max=400, message="Биография: до 400 символов")
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
        if (o == null || getClass() != o.getClass()) return false;
        Author authors = (Author) o;
        if (secondName != null ? !secondName.equals(authors.secondName) : authors.secondName != null) return false;
        if (firstName != null ? !firstName.equals(authors.firstName) : authors.firstName != null) return false;
        if (middleName != null ? !middleName.equals(authors.middleName) : authors.middleName != null) return false;
        if (birthYear != null ? !birthYear.equals(authors.birthYear) : authors.birthYear != null) return false;
        if (biography != null ? !biography.equals(authors.biography) : authors.biography != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (birthYear != null ? birthYear.hashCode() : 0);
        result = 31 * result + (biography != null ? biography.hashCode() : 0);
        return result;
    }

}
