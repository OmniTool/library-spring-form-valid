package net.library.spring.dto;

import net.library.spring.entities.*;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class BookDTO extends BaseDTO<Book> {

    @Size(min=1, max=64, message="Название: от 1 до 64 символов")
    private String title;
    private Integer pubYear;
    private GenreDTO genre;
    private List<BookAuthorDTO> authorsList = new ArrayList<>();

    public BookDTO() {}

    public BookDTO(Book book) {
        setEntity(book);
    }
    public Book getEntity() {
        Book book = new Book();
        book.setTitle(getTitle());
        book.setPubYear(getPubYear());
        book.setGenre(getGenre().getEntity());
        book.setAuthorsList(getBookAuthorList(getAuthorsList()));
        book.setId(book.getId());
        return book;
    }
    public void setEntity(Book book) {
        setTitle(book.getTitle());
        setPubYear(book.getPubYear());
        setGenre(new GenreDTO(book.getGenre()));
        setAuthorsList(getBookAuthorDTOList(book.getAuthorsList()));
        setId(book.getId());
    }
    public List<BookAuthorDTO> getAuthorsList() {
        return authorsList;
    }
    public void setAuthorsList(List<BookAuthorDTO> authorsList) {
        this.authorsList = authorsList;
    }
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
    public GenreDTO getGenre() {
        return genre;
    }
    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }
}