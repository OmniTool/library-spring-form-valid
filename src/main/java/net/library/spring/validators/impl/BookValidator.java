package net.library.spring.validators.impl;

import net.library.spring.dao.DAOBook;
import net.library.spring.entities.Book;
import net.library.spring.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Component
public class BookValidator implements Validator<Book> {

    @Autowired
    protected DAOBook dao;

    @Override
    public boolean exists(Book book) {
        book = trim(book);
        List<Book> list = dao.searchEntityByName(book);
        String title = book.getTitle().toUpperCase();
        int pubYear = book.getPubYear();
        int genreId = book.getGenre().getId();
        for (Book bookFound : list) {
            bookFound = trim(bookFound);
            if (bookFound.getTitle().toUpperCase().equals(title)
                    && bookFound.getPubYear()==pubYear
                    && bookFound.getGenre().getId()==genreId
                    && bookFound.getId() != book.getId())
                return true;
        }
        return false;
    }
    @Override
    public Book trim(Book book) {
        Book bookTrimmed = new Book();
        bookTrimmed.setTitle(StringUtils.trimToEmpty(book.getTitle()));
        bookTrimmed.setId(book.getId());
        bookTrimmed.setAuthorsList(book.getAuthorsList());
        bookTrimmed.setGenre(book.getGenre());
        bookTrimmed.setPubYear(book.getPubYear());
        return bookTrimmed;
    }
}
