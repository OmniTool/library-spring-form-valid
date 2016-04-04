package net.library.spring.entities;

import javax.persistence.*;

@Entity
@Table(name = "books_authors", schema = "public", catalog = "library_test")
public class BookAuthor extends EntityBase {

    @ManyToOne(targetEntity=Book.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne(targetEntity=Author.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    public BookAuthor() {}
    public BookAuthor(Book book, Author author) {
        this.book = book;
        this.author = author;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAuthor that = (BookAuthor) o;
        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        return !(author != null ? !author.equals(that.author) : that.author != null);
    }
    @Override
    public int hashCode() {
        int result = book != null ? book.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

}
