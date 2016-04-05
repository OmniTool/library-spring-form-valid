package net.library.spring.utils.converterDTO.impl;

import net.library.spring.dao.*;
import net.library.spring.dto.*;
import net.library.spring.entities.Book;
import net.library.spring.utils.converterDTO.ConverterEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterBookDTO implements ConverterEntityDTO<Book, BookDTO> {

    @Autowired private DAOAuthor daoAuthor;
    @Autowired private DAOBook daoBook;
    @Autowired private DAOGenre daoGenre;

    @Override
    public BookDTO packEntityToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setPubYear(book.getPubYear());
        bookDTO.setGenreId(book.getGenre().getId());
        bookDTO.setAuthorsIdList();
        bookDTO.setId(book.getId());
        return bookDTO;
    }
    @Override
    public Book unpackEntityFromDTO(BookDTO bookDTO) {
        int id = bookDTO.getId();
        Book book = (id == 0) ? new Book() : daoBook.getEntityById(id);
        book.setTitle(bookDTO.getTitle());
        book.setPubYear(bookDTO.getPubYear());
        book.setGenre();
        book.setAuthorsList();
        return book;
    }

}
