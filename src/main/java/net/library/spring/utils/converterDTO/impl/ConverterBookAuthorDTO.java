package net.library.spring.utils.converterDTO.impl;

import net.library.spring.dto.BookAuthorDTO;
import net.library.spring.entities.BookAuthor;
import net.library.spring.utils.converterDTO.ConverterEntityDTO;
import org.springframework.stereotype.Component;

@Component
public class ConverterBookAuthorDTO implements ConverterEntityDTO<BookAuthor, BookAuthorDTO> {

    @Override
    public BookAuthorDTO packEntityToDTO(BookAuthor bookAuthor) {
        return new BookAuthorDTO(bookAuthor);
    }
}
