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

        for (Author e : list) {
            trim(e);
            String firstNameE = e.getFirstName().toUpperCase();
            String middleNameE = e.getMiddleName().toUpperCase();
            String secondNameE = e.getSecondName().toUpperCase();
            int birthYearE = e.getBirthYear();
                if (middleNameE.equals(middleName)
                        && firstNameE.equals(firstName)
                        && secondNameE.equals(secondName)
                        && birthYearE==birthYear)
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
    @Override
    public boolean isNumber(String str) {
        return str != null && str.matches("-?\\+?\\d+");
    }
    @Override
    public boolean isEmptyString(String str) {
        return str == null || str.equals("") || str.matches("\\s+");
    }
}
