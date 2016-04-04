package net.library.spring.validators.impl;

import net.library.spring.dao.DAOGenre;
import net.library.spring.entities.Genre;
import net.library.spring.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreValidator implements Validator<Genre> {

    @Autowired
    protected DAOGenre dao;

    @Override
    public boolean exists(Genre entity) {
        trim(entity);
        List<Genre> list = dao.searchEntityByName(entity);
        String title = entity.getTitle().toUpperCase();
        for (Genre genre : list) {
            trim(genre);
            if (genre.getTitle().toUpperCase().equals(title)
                    && genre.getId() != entity.getId())
                return true;
        }
        return false;
    }
    @Override
    public void trim(Genre entity) {
        if (entity.getTitle() != null)
            entity.setTitle(entity.getTitle().trim());
        else
            entity.setTitle("");
        if (entity.getDescription() != null)
            entity.setDescription(entity.getDescription().trim());
        else
            entity.setDescription("");
    }
}
