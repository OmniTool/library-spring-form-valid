package net.library.spring.utils.converterDTO.impl;

import net.library.spring.dto.AuthorDTO;
import net.library.spring.entities.Author;
import net.library.spring.utils.converterDTO.ConverterEntityDTO;
import org.springframework.stereotype.Component;

@Component
public class ConverterAuthorDTO implements ConverterEntityDTO<Author, AuthorDTO> {

    @Override
    public AuthorDTO packEntityToDTO(Author author) {
        return new AuthorDTO(author);
    }
}
