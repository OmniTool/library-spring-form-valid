package net.library.spring.dto;

import net.library.spring.entities.BookAuthor;
import net.library.spring.entities.EntityBase;

public class BookAuthorDTO extends BaseDTO<BookAuthor> {

    private BookDTO book;
    private AuthorDTO author;

    public BookAuthorDTO() {}
    public BookAuthorDTO(BookDTO book, AuthorDTO author) {
        this.book = book;
        this.author = author;
    }
    public BookAuthorDTO(BookAuthor bookAuthor) {
        setEntity(bookAuthor);
    }

    public BookAuthor getEntity() {
        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setBook(getBook().getEntity());
        bookAuthor.setAuthor(getAuthor().getEntity());
        bookAuthor.setId(getId());
        return bookAuthor;
    }
    public void setEntity(BookAuthor bookAuthor) {
        setBook(new BookDTO(bookAuthor.getBook()));
        setAuthor(new AuthorDTO(bookAuthor.getAuthor()));
        setId(bookAuthor.getId());
    }

    public BookDTO getBook() {
        return book;
    }
    public void setBook(BookDTO book) {
        this.book = book;
    }
    public AuthorDTO getAuthor() {
        return author;
    }
    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}
