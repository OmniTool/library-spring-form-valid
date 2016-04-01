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

        for (Genre e : list) {
            trim(e);
            if (e.getTitle().toUpperCase().equals(title))
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
    @Override
    public boolean isNumber(String str) {
        return str != null && str.matches("-?\\+?\\d+");
    }
    @Override
    public boolean isEmptyString(String str) {
        return str == null || str.equals("") || str.matches("\\s+");
    }
}
