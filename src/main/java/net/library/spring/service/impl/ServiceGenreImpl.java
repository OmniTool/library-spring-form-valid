package net.library.spring.service.impl;

import net.library.spring.dao.DAOGenre;
import net.library.spring.dto.*;
import net.library.spring.entities.Genre;
import net.library.spring.service.ServiceGenre;
import net.library.spring.utils.converterDTO.impl.ConverterGenreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceGenreImpl extends ServiceBase<GenreDTO, DAOGenre, Genre> implements ServiceGenre {

    @Autowired
    public ServiceGenreImpl(DAOGenre dao, ConverterGenreDTO converter) {
        super(dao, converter);
    }
}
