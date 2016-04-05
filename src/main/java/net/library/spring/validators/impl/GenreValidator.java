package net.library.spring.validators.impl;

import net.library.spring.dao.DAOGenre;
import net.library.spring.entities.Genre;
import net.library.spring.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Component
public class GenreValidator implements Validator<Genre> {

    @Autowired
    protected DAOGenre dao;

    @Override
    public boolean exists(Genre genre) {
        genre = trim(genre);
        List<Genre> list = dao.searchEntityByName(genre);
        String title = genre.getTitle().toUpperCase();
        for (Genre genreFound : list) {
            genreFound = trim(genreFound);
            if (genreFound.getTitle().toUpperCase().equals(title)
                    && genreFound.getId() != genre.getId())
                return true;
        }
        return false;
    }
    @Override
    public Genre trim(Genre genre) {
        Genre genreTrimmed = new Genre();
        genreTrimmed.setTitle(StringUtils.trimToEmpty(genre.getTitle()));
        genreTrimmed.setDescription(StringUtils.trimToEmpty(genre.getDescription()));
        genreTrimmed.setId(genre.getId());
        return genreTrimmed;
    }
}
