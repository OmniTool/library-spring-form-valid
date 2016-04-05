package net.library.spring.dto;

import net.library.spring.entities.*;

public class BookAuthorDTO extends BaseDTO<BookAuthor> {

    private Integer bookId;
    private Integer authorId;

    public BookAuthorDTO() {}

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}