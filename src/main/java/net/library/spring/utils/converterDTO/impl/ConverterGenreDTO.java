package net.library.spring.utils.converterDTO.impl;

import net.library.spring.dto.GenreDTO;
import net.library.spring.entities.Genre;
import net.library.spring.utils.converterDTO.ConverterEntityDTO;
import org.springframework.stereotype.Component;

@Component
public class ConverterGenreDTO implements ConverterEntityDTO<Genre, GenreDTO> {

    @Override
    public GenreDTO packEntityToDTO(Genre genre) {
        return new GenreDTO(genre);
    }
}
