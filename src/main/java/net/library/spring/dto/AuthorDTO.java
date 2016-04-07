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
    private List<Integer> booksIdList = new ArrayList<>();

    public AuthorDTO() {}

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
    public List<Integer> getBooksIdList() {
        return booksIdList;
    }
    public void setBooksIdList(List<Integer> booksIdList) {
        this.booksIdList = booksIdList;
    }
}