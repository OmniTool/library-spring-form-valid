package net.library.spring.service.impl;

import net.library.spring.dao.DAOGenre;
import net.library.spring.entities.*;
import net.library.spring.service.ServiceGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceGenreImpl extends ServiceBase<Genre, DAOGenre> implements ServiceGenre {

    @Autowired
    public ServiceGenreImpl(DAOGenre dao) {
        super(dao);
    }
}
