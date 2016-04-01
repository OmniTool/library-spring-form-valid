package net.library.spring.validators.impl;

import net.library.spring.dao.DAOBook;
import net.library.spring.entities.Book;
import net.library.spring.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookValidator implements Validator<Book> {

    @Autowired
    protected DAOBook dao;

    @Override
    public boolean exists(Book entity) {
        trim(entity);
        List<Book> list = dao.searchEntityByName(entity);
        String title = entity.getTitle().toUpperCase();
        int pubYear = entity.getPubYear();
        int genreId = entity.getGenre().getId();

        for (Book e : list) {
            trim(e);
            if (e.getTitle().toUpperCase().equals(title)
                    && e.getPubYear()==pubYear
                    && e.getGenre().getId()==genreId)
                return true;
        }
        return false;
    }
    @Override
    public void trim(Book entity) {
        if (entity.getTitle() != null)
            entity.setTitle(entity.getTitle().trim());
        else
            entity.setTitle("");
    }
}
