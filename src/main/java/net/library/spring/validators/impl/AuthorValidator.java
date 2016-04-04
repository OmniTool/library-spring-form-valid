package net.library.spring.validators.impl;

import net.library.spring.dao.DAOAuthor;
import net.library.spring.entities.Author;
import net.library.spring.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorValidator implements Validator<Author> {

    @Autowired
    protected DAOAuthor dao;

    @Override
    public boolean exists(Author entity) {
        trim(entity);
        List<Author> list = dao.searchEntityByName(entity);
        String firstName = entity.getFirstName().toUpperCase();
        String middleName = entity.getMiddleName().toUpperCase();
        String secondName = entity.getSecondName().toUpperCase();
        int birthYear = entity.getBirthYear();
        for (Author author : list) {
            trim(author);
            if (author.getFirstName().toUpperCase().equals(firstName)
                    && author.getMiddleName().toUpperCase().equals(middleName)
                    && author.getSecondName().toUpperCase().equals(secondName)
                    && author.getBirthYear()==birthYear
                    && author.getId() != entity.getId())
                return true;
        }
        return false;
    }
    @Override
    public void trim(Author entity) {
        if (entity.getFirstName() != null)
            entity.setFirstName(entity.getFirstName().trim());
        else
            entity.setFirstName("");
        if (entity.getMiddleName() != null)
            entity.setMiddleName(entity.getMiddleName().trim());
        else
            entity.setMiddleName("");
        if (entity.getSecondName() != null)
            entity.setSecondName(entity.getSecondName().trim());
        else
            entity.setSecondName("");
        if (entity.getBiography() != null)
            entity.setBiography(entity.getBiography().trim());
        else
            entity.setBiography("");
    }
}
