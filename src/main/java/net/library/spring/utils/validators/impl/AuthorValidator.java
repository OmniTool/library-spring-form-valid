package net.library.spring.utils.validators.impl;

import net.library.spring.dto.AuthorDTO;
import net.library.spring.service.ServiceAuthor;
import net.library.spring.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Component
public class AuthorValidator implements Validator<AuthorDTO> {

    @Autowired private ServiceAuthor serviceAuthor;

    @Override
    public boolean exists(AuthorDTO author) {
        author = trim(author);
        List<AuthorDTO> list = serviceAuthor.searchEntityByName(author);
        String firstName = author.getFirstName().toUpperCase();
        String middleName = author.getMiddleName().toUpperCase();
        String secondName = author.getSecondName().toUpperCase();
        int birthYear = author.getBirthYear();
        for (AuthorDTO authorFound : list) {
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
    public AuthorDTO trim(AuthorDTO author) {
        AuthorDTO authorTrimmed = new AuthorDTO();
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
