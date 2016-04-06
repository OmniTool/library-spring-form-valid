package net.library.spring.utils.validators.impl;

import net.library.spring.dto.BookDTO;
import net.library.spring.service.ServiceBook;
import net.library.spring.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Component
public class BookValidator implements Validator<BookDTO> {

    @Autowired private ServiceBook serviceBook;

    @Override
    public boolean exists(BookDTO book) {
        book = trim(book);
        List<BookDTO> list = serviceBook.searchEntityByName(book);
        String title = book.getTitle().toUpperCase();
        int pubYear = book.getPubYear();
        int genreId = book.getGenreId();
        for (BookDTO bookFound : list) {
            bookFound = trim(bookFound);
            if (bookFound.getTitle().toUpperCase().equals(title)
                    && bookFound.getPubYear()==pubYear
                    && bookFound.getGenreId()==genreId
                    && bookFound.getId() != book.getId())
                return true;
        }
        return false;
    }
    @Override
    public BookDTO trim(BookDTO book) {
        BookDTO bookTrimmed = new BookDTO();
        bookTrimmed.setTitle(StringUtils.trimToEmpty(book.getTitle()));
        bookTrimmed.setId(book.getId());
        bookTrimmed.setAuthorsIdList(book.getAuthorsIdList());
        bookTrimmed.setGenreId(book.getGenreId());
        bookTrimmed.setPubYear(book.getPubYear());
        return bookTrimmed;
    }
}
