package net.library.spring.dto;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class BookDTO extends BaseDTO<BookDTO> {

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

    public boolean isIdentical(BookDTO book) {
        return getTitle().toUpperCase().equals(book.getTitle().toUpperCase())
                && getPubYear().equals(book.getPubYear())
                && getGenreId().equals(book.getGenreId())
                && getId() != book.getId();
    }
    public BookDTO trim() {
        BookDTO bookTrimmed = new BookDTO();
        bookTrimmed.setTitle(StringUtils.trimToEmpty(getTitle()));
        bookTrimmed.setId(getId());
        bookTrimmed.setAuthorsIdList(getAuthorsIdList());
        bookTrimmed.setGenreId(getGenreId());
        bookTrimmed.setPubYear(getPubYear());
        return bookTrimmed;
    }
}