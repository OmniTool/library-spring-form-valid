package net.library.spring.utils.converterDTO.impl;

import net.library.spring.dao.*;
import net.library.spring.dto.BookAuthorDTO;
import net.library.spring.entities.BookAuthor;
import net.library.spring.utils.converterDTO.ConverterEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterBookAuthorDTO implements ConverterEntityDTO<BookAuthor, BookAuthorDTO> {

    @Autowired private DAOBookAuthor daoBookAuthor;
    @Autowired private DAOAuthor daoAuthor;
    @Autowired private DAOBook daoBook;

    @Override
    public BookAuthorDTO packEntityToDTO(BookAuthor bookAuthor) {
        BookAuthorDTO bookAuthorDTO = new BookAuthorDTO();
        bookAuthorDTO.setBookId(bookAuthor.getBook().getId());
        bookAuthorDTO.setAuthorId(bookAuthor.getAuthor().getId());
        bookAuthorDTO.setId(bookAuthor.getId());
        return bookAuthorDTO;
    }
    @Override
    public BookAuthor unpackEntityFromDTO(BookAuthorDTO bookAuthorDTO) {
        int id = bookAuthorDTO.getId();
        BookAuthor bookAuthor = (id == 0) ? new BookAuthor() : daoBookAuthor.getEntityById(id);
        bookAuthor.setBook();
        bookAuthor.setAuthor();
        return bookAuthor;
    }

}
