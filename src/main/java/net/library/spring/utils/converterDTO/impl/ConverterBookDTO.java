package net.library.spring.utils.converterDTO.impl;

import net.library.spring.dto.BookDTO;
import net.library.spring.entities.Book;
import net.library.spring.utils.converterDTO.ConverterEntityDTO;
import org.springframework.stereotype.Component;

@Component
public class ConverterBookDTO implements ConverterEntityDTO<Book, BookDTO> {

    @Override
    public BookDTO packEntityToDTO(Book book) {
        return new BookDTO(book);
    }
}
