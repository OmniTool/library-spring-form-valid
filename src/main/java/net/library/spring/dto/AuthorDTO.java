package net.library.spring.dto;

import net.library.spring.entities.*;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class AuthorDTO extends BaseDTO<Author> {

    @Size(min=1, max=64, message="Фамилия: от 1 до 64 символов")
    private String secondName;
    @Size(min=1, max=64, message="Имя: от 1 до 64 символов")
    private String firstName;
    @Size(max=64, message="Отчество: до 64 символов")
    private String middleName;
    private Integer birthYear;
    @Size(max=400, message="Биография: до 400 символов")
    private String biography;
    private List<BookAuthorDTO> booksList = new ArrayList<>();

    public AuthorDTO() {}

    public AuthorDTO(Author author) {
        setEntity(author);
    }
    @Override
    public Author getEntity() {
        Author author = new Author();
        author.setSecondName(getSecondName());
        author.setFirstName(getFirstName());
        author.setMiddleName(getMiddleName());
        author.setBirthYear(getBirthYear());
        author.setBiography(getBiography());
        author.setBooksList(getBookAuthorList(getBooksList()));
        author.setId(getId());
        return author;
    }
    @Override
    public void setEntity(Author author) {
        setSecondName(author.getSecondName());
        setFirstName(author.getFirstName());
        setMiddleName(author.getMiddleName());
        setBirthYear(author.getBirthYear());
        setBiography(author.getBiography());
        setBooksList(getBookAuthorDTOList(author.getBooksList()));
        setId(author.getId());
    }
    public List<BookAuthorDTO> getBooksList() {
        return booksList;
    }
    public void setBooksList(List<BookAuthorDTO> booksList) {
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