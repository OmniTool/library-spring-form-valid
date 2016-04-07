package net.library.spring.utils.validators.impl;

import net.library.spring.dto.AuthorDTO;
import net.library.spring.entities.Author;
import net.library.spring.service.ServiceAuthor;
import net.library.spring.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorValidator extends ValidatorBase<AuthorDTO, Author> implements Validator<AuthorDTO> {

    @Autowired
    public AuthorValidator(ServiceAuthor serviceAuthor) { super(serviceAuthor); }

}
