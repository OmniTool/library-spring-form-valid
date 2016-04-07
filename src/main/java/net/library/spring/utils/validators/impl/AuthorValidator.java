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
        for (AuthorDTO authorFound : list) {
            authorFound = trim(authorFound);
            if (areIdentical(author, authorFound)) return true;
        }
        return false;
    }
    private boolean areIdentical(AuthorDTO author, AuthorDTO authorFound) {
        return authorFound.getFirstName().toUpperCase().equals(author.getFirstName().toUpperCase())
                && authorFound.getMiddleName().toUpperCase().equals(author.getMiddleName().toUpperCase())
                && authorFound.getSecondName().toUpperCase().equals(author.getSecondName().toUpperCase())
                && authorFound.getBirthYear().equals(author.getBirthYear())
                && authorFound.getId() != author.getId();
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
        authorTrimmed.setBooksIdList(author.getBooksIdList());
        return authorTrimmed;
    }
}
