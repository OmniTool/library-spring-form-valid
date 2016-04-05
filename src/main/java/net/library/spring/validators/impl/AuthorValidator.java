package net.library.spring.validators.impl;

import net.library.spring.dao.DAOAuthor;
import net.library.spring.entities.Author;
import net.library.spring.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Component
public class AuthorValidator implements Validator<Author> {

    @Autowired
    protected DAOAuthor dao;

    @Override
    public boolean exists(Author author) {
        author = trim(author);
        List<Author> list = dao.searchEntityByName(author);
        String firstName = author.getFirstName().toUpperCase();
        String middleName = author.getMiddleName().toUpperCase();
        String secondName = author.getSecondName().toUpperCase();
        int birthYear = author.getBirthYear();
        for (Author authorFound : list) {
            authorFound = trim(authorFound);
            if (authorFound.getFirstName().toUpperCase().equals(firstName)
                    && authorFound.getMiddleName().toUpperCase().equals(middleName)
                    && authorFound.getSecondName().toUpperCase().equals(secondName)
                    && authorFound.getBirthYear()==birthYear
                    && authorFound.getId() != author.getId())
                return true;
        }
        return false;
    }
    @Override
    public Author trim(Author author) {
        Author authorTrimmed = new Author();
        authorTrimmed.setFirstName(StringUtils.trimToEmpty(author.getFirstName()));
        authorTrimmed.setMiddleName(StringUtils.trimToEmpty(author.getMiddleName()));
        authorTrimmed.setSecondName(StringUtils.trimToEmpty(author.getSecondName()));
        authorTrimmed.setBiography(StringUtils.trimToEmpty(author.getBiography()));
        authorTrimmed.setId(author.getId());
        authorTrimmed.setBirthYear(author.getBirthYear());
        authorTrimmed.setBooksList(author.getBooksList());
        return authorTrimmed;
    }
}
