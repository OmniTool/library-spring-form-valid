package net.library.spring.dto;

public class BookAuthorDTO extends BaseDTO<BookAuthorDTO> {

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

    @Override
    public BookAuthorDTO trim() { return this; }

    @Override
    public boolean isIdenticalExceptId(BookAuthorDTO bookAuthor) {
        return getBookId().equals(bookAuthor.getBookId())
                && getAuthorId().equals(bookAuthor.getAuthorId());
    }


}