package net.library.spring.service;

import net.library.spring.entities.Author;
import net.library.spring.entities.Book;
import net.library.spring.entities.BookAuthor;

import java.util.List;

public interface ServiceBookAuthor extends Service<BookAuthor, Integer> {

    List<Book> searchBooksByAuthor(Author entity);
    List<Author> searchAuthorsByBook(Book entity);
}