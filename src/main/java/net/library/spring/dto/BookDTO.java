package net.library.spring.dto;

import net.library.spring.entities.*;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class BookDTO extends BaseDTO<Book> {

    @Size(min=1, max=64, message="Название: от 1 до 64 символов")
    private String title;
    private Integer pubYear;
    private Integer genreId;
    private List<Integer> authorsIdList = new ArrayList<>();

    public BookDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPubYear() {
        return pubYear;
    }

    public void setPubYear(Integer pubYear) {
        this.pubYear = pubYear;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public List<Integer> getAuthorsIdList() {
        return authorsIdList;
    }

    public void setAuthorsIdList(List<Integer> authorsIdList) {
        this.authorsIdList = authorsIdList;
    }
}