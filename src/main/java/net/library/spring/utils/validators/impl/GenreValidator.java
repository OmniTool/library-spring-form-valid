package net.library.spring.utils.validators.impl;

import net.library.spring.dto.GenreDTO;
import net.library.spring.service.ServiceGenre;
import net.library.spring.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Component
public class GenreValidator implements Validator<GenreDTO> {

    @Autowired private ServiceGenre serviceGenre;

    @Override
    public boolean exists(GenreDTO genre) {
        genre = trim(genre);
        List<GenreDTO> list = serviceGenre.searchEntityByName(genre);
        String title = genre.getTitle().toUpperCase();
        for (GenreDTO genreFound : list) {
            genreFound = trim(genreFound);
            if (genreFound.getTitle().toUpperCase().equals(title)
                    && genreFound.getId() != genre.getId())
                return true;
        }
        return false;
    }
    @Override
    public GenreDTO trim(GenreDTO genre) {
        GenreDTO genreTrimmed = new GenreDTO();
        genreTrimmed.setTitle(StringUtils.trimToEmpty(genre.getTitle()));
        genreTrimmed.setDescription(StringUtils.trimToEmpty(genre.getDescription()));
        genreTrimmed.setId(genre.getId());
        return genreTrimmed;
    }
}
