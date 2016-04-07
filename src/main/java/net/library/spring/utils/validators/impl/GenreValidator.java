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
        for (GenreDTO genreFound : list) {
            genreFound = trim(genreFound);
            if (areIdentical(genre, genreFound)) return true;
        }
        return false;
    }
    private boolean areIdentical(GenreDTO genre, GenreDTO genreFound) {
        return genreFound.getTitle().toUpperCase().equals(genre.getTitle().toUpperCase())
                && genreFound.getId() != genre.getId();
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
