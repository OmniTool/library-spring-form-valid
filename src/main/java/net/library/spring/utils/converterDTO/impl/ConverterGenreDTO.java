package net.library.spring.utils.converterDTO.impl;

import net.library.spring.dao.DAOGenre;
import net.library.spring.dto.GenreDTO;
import net.library.spring.entities.Genre;
import net.library.spring.utils.converterDTO.ConverterEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterGenreDTO implements ConverterEntityDTO<Genre, GenreDTO> {

    @Autowired
    private DAOGenre daoGenre;

    @Override
    public GenreDTO packEntityToDTO(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setTitle(genre.getTitle());
        genreDTO.setDescription(genre.getDescription());
        genreDTO.setId(genre.getId());
        return genreDTO;
    }
    @Override
    public Genre unpackEntityFromDTO(GenreDTO genreDTO) {
        int id = genreDTO.getId();
        Genre genre = (id == 0) ? new Genre() : daoGenre.getEntityById(id);
        genre.setTitle(genreDTO.getTitle());
        genre.setDescription(genreDTO.getDescription());
        return genre;
    }

}
