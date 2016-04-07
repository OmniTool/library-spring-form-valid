package net.library.spring.utils.validators.impl;

import net.library.spring.dto.GenreDTO;
import net.library.spring.entities.Genre;
import net.library.spring.service.ServiceGenre;
import net.library.spring.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenreValidator extends ValidatorBase<GenreDTO, Genre> implements Validator<GenreDTO> {

    @Autowired
    public GenreValidator(ServiceGenre serviceGenre) { super(serviceGenre); }

}
